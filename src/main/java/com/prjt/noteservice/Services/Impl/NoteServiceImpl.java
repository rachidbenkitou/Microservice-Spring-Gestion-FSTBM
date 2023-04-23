package com.prjt.noteservice.Services.Impl;

import com.prjt.noteservice.DAO.NoteDAO;
import com.prjt.noteservice.DTO.RequesteNoteDTO;
import com.prjt.noteservice.DTO.ResponseNoteDTO;
import com.prjt.noteservice.Entities.Etudiant;
import com.prjt.noteservice.Entities.Examen;
import com.prjt.noteservice.Entities.Note;
import com.prjt.noteservice.Entities.NoteKey;
import com.prjt.noteservice.Exceptions.ExamenNotFoundException;
import com.prjt.noteservice.Exceptions.NoteNotFoundException;
import com.prjt.noteservice.Mappers.NoteMapper;
import com.prjt.noteservice.Openfeign.EtudiantRestClient;
import com.prjt.noteservice.Services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDAO noteDAO;
    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private EtudiantRestClient etudiantRestClient;
    @Override
    public List<ResponseNoteDTO> getAllNotes() {
        List<Note> noteList =noteDAO.findAll();
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
    public ResponseNoteDTO addNote(RequesteNoteDTO requesteNoteDTO) {
        Note note = noteMapper.dtoToModel(requesteNoteDTO);
        Note savedNote = noteDAO.save(note);
        return noteMapper.modelToDto(savedNote);
    }

    @Override
    public ResponseNoteDTO UpdateNote(RequesteNoteDTO requesteNoteDTO) {
        Note note = noteMapper.dtoToModel(requesteNoteDTO);
        Note savedNote = noteDAO.save(note);
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
}
