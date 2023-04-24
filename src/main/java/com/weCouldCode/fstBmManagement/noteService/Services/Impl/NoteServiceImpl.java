package com.weCouldCode.fstBmManagement.noteService.Services.Impl;

import com.weCouldCode.fstBmManagement.noteService.DAO.NoteDAO;
import com.weCouldCode.fstBmManagement.noteService.DTO.RequesteNoteDTO;
import com.weCouldCode.fstBmManagement.noteService.Entities.Etudiant;
import com.weCouldCode.fstBmManagement.noteService.Entities.Note;
import com.weCouldCode.fstBmManagement.noteService.Exceptions.NoteNotFoundException;
import com.weCouldCode.fstBmManagement.noteService.Openfeign.EtudiantRestClient;
import com.weCouldCode.fstBmManagement.noteService.Services.NoteService;
import com.weCouldCode.fstBmManagement.noteService.DTO.ResponseNoteDTO;
import com.weCouldCode.fstBmManagement.noteService.Entities.NoteKey;
import com.weCouldCode.fstBmManagement.noteService.Mappers.NoteMapper;
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
            throw new NoteNotFoundException("Il n' y a aucune note avec cet ID");
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
