package com.weCouldCode.fstBmManagement.noteService.Controller;

import com.weCouldCode.fstBmManagement.noteService.DTO.RequesteNoteDTO;
import com.weCouldCode.fstBmManagement.noteService.Exceptions.ExamenNotFoundException;
import com.weCouldCode.fstBmManagement.noteService.Exceptions.NoteNotFoundException;
import com.weCouldCode.fstBmManagement.noteService.DTO.ResponseNoteDTO;
import com.weCouldCode.fstBmManagement.noteService.Entities.NoteKey;
import com.weCouldCode.fstBmManagement.noteService.Services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api3")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    List<ResponseNoteDTO> getAllNotes(){

        return noteService.getAllNotes();
    }
    @GetMapping("/notes/{etudiantId}/{examenId}")
    ResponseNoteDTO getNoteById(@PathVariable(name = "etudiantId") long etudiantId , @PathVariable(name = "examenId") long examenId ) throws ExamenNotFoundException, NoteNotFoundException {
        NoteKey id = NoteKey.builder().etudiantId(etudiantId).examenId(examenId).build();
        return noteService.getNoteById(id);
    }
    @PostMapping("/save")
    ResponseNoteDTO save(@RequestBody RequesteNoteDTO requesteNoteDTO){
        return noteService.addNote(requesteNoteDTO);
    }

    @PutMapping ("/update/{id}")
    ResponseNoteDTO update(@PathVariable(name = "id") NoteKey id ,@RequestBody RequesteNoteDTO requesteNoteDTO){
        requesteNoteDTO.setId(id);
        return noteService.UpdateNote(requesteNoteDTO);
    }
    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable(name = "id") NoteKey id){
        noteService.deleteNote(id);
    }
}
