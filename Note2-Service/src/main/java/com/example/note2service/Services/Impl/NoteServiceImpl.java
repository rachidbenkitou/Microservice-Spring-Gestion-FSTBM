package com.example.note2service.Services.Impl;

import com.example.note2service.DAO.ExamenDAO;
import com.example.note2service.DAO.NoteDAO;
import com.example.note2service.DTO.RequesteNoteDTO;
import com.example.note2service.DTO.ResponseNoteDTO;
import com.example.note2service.Entities.Etudiant;
import com.example.note2service.Entities.Note;
import com.example.note2service.Entities.NoteKey;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import com.example.note2service.Exceptions.NoteNotFoundException;
import com.example.note2service.Mappers.ExamenMapper;
import com.example.note2service.Mappers.NoteMapper;
import com.example.note2service.Openfeign.EtudiantRestClient;
import com.example.note2service.Services.ExamenService;
import com.example.note2service.Services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDAO noteDAO;
    @Autowired
    private ExamenDAO examenDAO;
    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private EtudiantRestClient etudiantRestClient;

    @Override
    public List<ResponseNoteDTO> getAllNotes() {
        List<Note> noteList =noteDAO.findAll();
        for ( Note n : noteList){
            n.setEtudiant(etudiantRestClient.getEtudiant(n.getId().getEtudiantId()));

        }
        return noteMapper.modelToDtos(noteList);
    }

    @Override
    public ResponseNoteDTO getNoteById(NoteKey id) throws NoteNotFoundException {
        Optional<Note> note =  noteDAO.findById(id);
        Etudiant etudiant = etudiantRestClient.getEtudiant(note.get().getId().getEtudiantId());
        note.get().setEtudiant(etudiant);
        if (!note.isPresent()){
            throw new NoteNotFoundException("Il n' y a aucune note avec ce ID");
        }
        return noteMapper.modelToDto(note.get());

    }

    @Override
    public ResponseNoteDTO addNote(RequesteNoteDTO requesteNoteDTO) throws ExamenNotFoundException {
        Note note = noteMapper.dtoToModel(requesteNoteDTO);
        note.setMention(mention(note.getNote()));
        note.setExamen(examenDAO.findById(note.getId().getExamenId()).get());
        Note savedNote = noteDAO.save(note);
        savedNote.setEtudiant(etudiantRestClient.getEtudiant(savedNote.getId().getEtudiantId()));
        return noteMapper.modelToDto(savedNote);
    }

    @Override
    public ResponseNoteDTO UpdateNote(RequesteNoteDTO requesteNoteDTO) {
        Note note = noteMapper.dtoToModel(requesteNoteDTO);
        note.setMention(mention(note.getNote()));
        Note savedNote = noteDAO.save(note);
        savedNote.setEtudiant(etudiantRestClient.getEtudiant(savedNote.getId().getEtudiantId()));
        return noteMapper.modelToDto(savedNote);
    }

    @Override
    public ResponseNoteDTO NoteByEtudiant(long etudiantId) {
        return null;
    }

    @Override
    public void deleteNote(NoteKey noteId) {
                noteDAO.deleteById(noteId);
    }

    public String mention(double note){
        String mention;
        if(note >=16){
            mention="Tres Bien";
        } else if (note>=14 && note<16) {

            mention="Bien";
        } else if (note>=12 && note<14) {
            mention="Assez Bien";
        } else if (note>=10 && note<12) {
            mention="Passable";
        }else {
            mention="nv";
        }
        return mention;
    }
}
