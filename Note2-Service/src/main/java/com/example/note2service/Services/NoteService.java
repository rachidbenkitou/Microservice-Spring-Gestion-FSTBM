package com.example.note2service.Services;

import com.example.note2service.DTO.RequesteNoteDTO;
import com.example.note2service.DTO.ResponseNoteDTO;
import com.example.note2service.Entities.NoteKey;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import com.example.note2service.Exceptions.NoteNotFoundException;

import java.util.List;

public interface NoteService {
    List<ResponseNoteDTO> getAllNotes();
    ResponseNoteDTO getNoteById(NoteKey id) throws ExamenNotFoundException, NoteNotFoundException;
    List<ResponseNoteDTO> getNoteByCin(String cin);
    List<ResponseNoteDTO> getNoteByCinAndModuleId(String cin , long id);
    ResponseNoteDTO addNote(RequesteNoteDTO requesteNoteDTO) throws ExamenNotFoundException, NoteNotFoundException;
    ResponseNoteDTO UpdateNote(RequesteNoteDTO requesteNoteDTO);
//    ResponseNoteDTO NoteByEtudiant(long etudiantId);
    void  deleteNote(NoteKey noteId);
}
