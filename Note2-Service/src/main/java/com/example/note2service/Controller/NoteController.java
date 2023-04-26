package com.example.note2service.Controller;

import com.example.note2service.DTO.RequesteNoteDTO;
import com.example.note2service.DTO.ResponseExamenDTO;
import com.example.note2service.DTO.ResponseModuleDTO;
import com.example.note2service.DTO.ResponseNoteDTO;

import com.example.note2service.Entities.NoteKey;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import com.example.note2service.Exceptions.NoteNotFoundException;
import com.example.note2service.Services.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notes")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Operation(summary = "Get all notes", description = "This method allows you to find all notes and returns a list of ResponseNoteDTO objects.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The notes of objects were found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseNoteDTO.class))})
    })
    @GetMapping
    List<ResponseNoteDTO> getAllNotes(){

        return noteService.getAllNotes();
    }
    @Operation(summary = "Get note by id", description = "This method allows you to find the Notes by Id and returns a  ResponseNoteDTO object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseNoteDTO.class))})
    })
    @GetMapping("/{etudiantId}/{examenId}")
    ResponseNoteDTO getNoteById(@PathVariable(name = "etudiantId") long etudiantId , @PathVariable(name = "examenId") long examenId ) throws ExamenNotFoundException, NoteNotFoundException {
        NoteKey id = NoteKey.builder().etudiantId(etudiantId).examenId(examenId).build();
        return noteService.getNoteById(id);
    }
    @Operation(summary = "Add note", description = "this method allows you to add a Note.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Note object created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseNoteDTO.class))})
    })
    @PostMapping
    ResponseNoteDTO save(@RequestBody RequesteNoteDTO requesteNoteDTO){
        return noteService.addNote(requesteNoteDTO);
    }
    @Operation(summary = "Update note", description = "this method allows you to update a Note.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Note object modified",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseNoteDTO.class))})
    })
    @PutMapping ("/{etudiantId}/{examenId}")
    ResponseNoteDTO update(@PathVariable(name = "etudiantId") long etudiantId,@PathVariable(name = "examenId") long examentId ,@RequestBody RequesteNoteDTO requesteNoteDTO){
        NoteKey id = NoteKey.builder().etudiantId(etudiantId).examenId(examentId).build();
        requesteNoteDTO.setId(id);
        return noteService.UpdateNote(requesteNoteDTO);
    }
    @Operation(summary = "delete note", description = "this method allows you to delete a Note by Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Note object deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseNoteDTO.class))})
    })
    @DeleteMapping("/{etudiantId}/{examenId}")
    void delete(@PathVariable(name = "etudiantId") long etudiantId,@PathVariable(name = "examenId") long examentId){
        NoteKey id = NoteKey.builder().etudiantId(etudiantId).examenId(examentId).build();
        noteService.deleteNote(id);
    }
}
