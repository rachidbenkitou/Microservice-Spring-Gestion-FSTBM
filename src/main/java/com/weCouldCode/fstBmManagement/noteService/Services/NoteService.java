package com.weCouldCode.fstBmManagement.noteService.Services;

import com.weCouldCode.fstBmManagement.noteService.DTO.RequesteNoteDTO;
import com.weCouldCode.fstBmManagement.noteService.Exceptions.ExamenNotFoundException;
import com.weCouldCode.fstBmManagement.noteService.Exceptions.NoteNotFoundException;
import com.weCouldCode.fstBmManagement.noteService.DTO.ResponseNoteDTO;
import com.weCouldCode.fstBmManagement.noteService.Entities.NoteKey;

import java.util.List;

public interface NoteService {
    List<ResponseNoteDTO> getAllNotes();
    ResponseNoteDTO getNoteById(NoteKey id) throws ExamenNotFoundException, NoteNotFoundException;
    ResponseNoteDTO addNote(RequesteNoteDTO requesteNoteDTO);
    ResponseNoteDTO UpdateNote(RequesteNoteDTO requesteNoteDTO);
    ResponseNoteDTO NoteByEtudiant(long etudiantId);
    void  deleteNote(NoteKey noteId);
}
