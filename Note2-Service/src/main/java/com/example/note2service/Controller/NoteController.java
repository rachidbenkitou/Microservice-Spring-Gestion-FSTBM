package com.example.note2service.Controller;

import com.example.note2service.Controller.api.NoteApi;
import com.example.note2service.DAO.NoteDAO;
import com.example.note2service.DTO.RequesteNoteDTO;
import com.example.note2service.DTO.ResponseNoteDTO;
import com.example.note2service.Entities.Module;
import com.example.note2service.Entities.Note;
import com.example.note2service.Entities.NoteKey;
import com.example.note2service.Entities.TypeExamen;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import com.example.note2service.Exceptions.NoteNotFoundException;
import com.example.note2service.Services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoteController implements NoteApi {
    private final NoteService noteService;
    private final NoteDAO dao;
    @Override
    public List<ResponseNoteDTO> getAllNotes(){

        return noteService.getAllNotes();
    }
    @Override
    public ResponseNoteDTO getNoteById(@PathVariable(name = "etudiantId") String etudiantId , @PathVariable(name = "examenId") long examenId ) throws ExamenNotFoundException, NoteNotFoundException {
        NoteKey id = NoteKey.builder().etudiantId(etudiantId).examenId(examenId).build();
        return noteService.getNoteById(id);
    }
    @Override
    public ResponseNoteDTO save(@RequestBody RequesteNoteDTO requesteNoteDTO) throws ExamenNotFoundException, NoteNotFoundException {
        return noteService.addNote(requesteNoteDTO);
    }
    @Override
    public ResponseNoteDTO update(@PathVariable(name = "etudiantId") String etudiantId,@PathVariable(name = "examenId") long examentId ,@RequestBody RequesteNoteDTO requesteNoteDTO){
        NoteKey id = NoteKey.builder().etudiantId(etudiantId).examenId(examentId).build();
        requesteNoteDTO.setId(id);
        return noteService.UpdateNote(requesteNoteDTO);
    }

    @Override
    public List<ResponseNoteDTO> getAllByCin(String cin) {
        return noteService.getNoteByCin(cin);
    }

    @Override
    public List<ResponseNoteDTO> getAllByCinModuleId(String cin, long id) {
        return noteService.getNoteByCinAndModuleId(cin,id);
    }

    @Override
    public void delete(@PathVariable(name = "etudiantId") String etudiantId,@PathVariable(name = "examenId") long examentId){
        NoteKey id = NoteKey.builder().etudiantId(etudiantId).examenId(examentId).build();
        noteService.deleteNote(id);
    }

    @GetMapping("/test/{id}/{ide}/{moduleName}")
    public Note testNote(@PathVariable TypeExamen id, @PathVariable long ide, @PathVariable String  moduleName){
        TypeExamen typeExamen=TypeExamen.ORDINAIRE;
        return dao.findByEtudiantNameAndTypeExamenAndNomModule(typeExamen,12, "analyse1");
    }

}
