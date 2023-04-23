package com.prjt.noteservice.Services;

import com.prjt.noteservice.DTO.RequestExamenDTO;
import com.prjt.noteservice.DTO.RequesteNoteDTO;
import com.prjt.noteservice.DTO.ResponseExamenDTO;
import com.prjt.noteservice.DTO.ResponseNoteDTO;
import com.prjt.noteservice.Entities.NoteKey;
import com.prjt.noteservice.Exceptions.ExamenNotFoundException;
import com.prjt.noteservice.Exceptions.NoteNotFoundException;

import java.util.List;

public interface NoteService {
    List<ResponseNoteDTO> getAllNotes();
    ResponseNoteDTO getNoteById(NoteKey id) throws ExamenNotFoundException, NoteNotFoundException;
    ResponseNoteDTO addNote(RequesteNoteDTO requesteNoteDTO);
    ResponseNoteDTO UpdateNote(RequesteNoteDTO requesteNoteDTO);
    ResponseNoteDTO NoteByEtudiant(long etudiantId);
    void  deleteNote(NoteKey noteId);
}
