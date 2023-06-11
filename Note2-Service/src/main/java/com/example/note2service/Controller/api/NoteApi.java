package com.example.note2service.Controller.api;

import com.example.note2service.DTO.RequesteNoteDTO;
import com.example.note2service.DTO.ResponseNoteDTO;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import com.example.note2service.Exceptions.NoteNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notes")
public interface NoteApi {
    @Operation(summary = "Get all notes", description = "This method allows you to find all notes and returns a list of ResponseNoteDTO objects.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The notes of objects were found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseNoteDTO.class))})
    })
    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    List<ResponseNoteDTO> getAllNotes();

    @Operation(summary = "Get note by id", description = "This method allows you to find the Notes by Id and returns a  ResponseNoteDTO object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseNoteDTO.class))})
    })
    @GetMapping("/{etudiantId}/{examenId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    ResponseNoteDTO getNoteById(@PathVariable(name = "etudiantId") String etudiantId , @PathVariable(name = "examenId") long examenId ) throws ExamenNotFoundException, NoteNotFoundException;

    @Operation(summary = "Add note", description = "this method allows you to add a Note.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Note object created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseNoteDTO.class))})
    })
    @PostMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    ResponseNoteDTO save(@RequestBody RequesteNoteDTO requesteNoteDTO) throws ExamenNotFoundException, NoteNotFoundException;

    @Operation(summary = "Update note", description = "this method allows you to update a Note.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Note object modified",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseNoteDTO.class))})
    })
    @PutMapping ("/{etudiantId}/{examenId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    ResponseNoteDTO update(@PathVariable(name = "etudiantId") String etudiantId,@PathVariable(name = "examenId") long examentId ,@RequestBody RequesteNoteDTO requesteNoteDTO);

    @GetMapping("cin/{cin}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    List<ResponseNoteDTO> getAllByCin(@PathVariable(name = "cin") String cin);
    @GetMapping("/{cin}/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    List<ResponseNoteDTO> getAllByCinModuleId(@PathVariable(name = "cin") String cin,@PathVariable(name = "id") long id);
    @Operation(summary = "delete note", description = "this method allows you to delete a Note by Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Note object deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseNoteDTO.class))})
    })
    @DeleteMapping("/{etudiantId}/{examenId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    void delete(@PathVariable(name = "etudiantId") String etudiantId,@PathVariable(name = "examenId") long examentId);
}
