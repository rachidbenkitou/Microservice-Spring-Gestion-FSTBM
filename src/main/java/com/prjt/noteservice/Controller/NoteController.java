package com.prjt.noteservice.Controller;

import com.prjt.noteservice.DTO.RequestExamenDTO;
import com.prjt.noteservice.DTO.RequesteNoteDTO;
import com.prjt.noteservice.DTO.ResponseExamenDTO;
import com.prjt.noteservice.DTO.ResponseNoteDTO;
import com.prjt.noteservice.Entities.NoteKey;
import com.prjt.noteservice.Exceptions.ExamenNotFoundException;
import com.prjt.noteservice.Exceptions.NoteNotFoundException;
import com.prjt.noteservice.Services.NoteService;
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
