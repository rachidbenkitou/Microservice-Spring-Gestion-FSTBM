package com.example.note2service.Controller;

import com.example.note2service.Controller.api.NoteApi;
import com.example.note2service.DTO.RequesteNoteDTO;
import com.example.note2service.DTO.ResponseNoteDTO;
import com.example.note2service.Entities.NoteKey;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import com.example.note2service.Exceptions.NoteNotFoundException;
import com.example.note2service.Services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoteController implements NoteApi {
    private final NoteService noteService;
    @Override
    public List<ResponseNoteDTO> getAllNotes(){

        return noteService.getAllNotes();
    }
    @Override
    public ResponseNoteDTO getNoteById(@PathVariable(name = "etudiantId") long etudiantId , @PathVariable(name = "examenId") long examenId ) throws ExamenNotFoundException, NoteNotFoundException {
        NoteKey id = NoteKey.builder().etudiantId(etudiantId).examenId(examenId).build();
        return noteService.getNoteById(id);
    }
    @Override
    public ResponseNoteDTO save(@RequestBody RequesteNoteDTO requesteNoteDTO) throws ExamenNotFoundException {
        return noteService.addNote(requesteNoteDTO);
    }
    @Override
    public ResponseNoteDTO update(@PathVariable(name = "etudiantId") long etudiantId,@PathVariable(name = "examenId") long examentId ,@RequestBody RequesteNoteDTO requesteNoteDTO){
        NoteKey id = NoteKey.builder().etudiantId(etudiantId).examenId(examentId).build();
        requesteNoteDTO.setId(id);
        return noteService.UpdateNote(requesteNoteDTO);
    }

    @Override
    public void delete(@PathVariable(name = "etudiantId") long etudiantId,@PathVariable(name = "examenId") long examentId){
        NoteKey id = NoteKey.builder().etudiantId(etudiantId).examenId(examentId).build();
        noteService.deleteNote(id);
    }
}
