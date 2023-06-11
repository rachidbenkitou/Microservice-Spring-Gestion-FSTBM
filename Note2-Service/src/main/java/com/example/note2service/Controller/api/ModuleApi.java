package com.example.note2service.Controller.api;

import com.example.note2service.DTO.RequestModuleDTO;
import com.example.note2service.DTO.ResponseModuleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/modules")
public interface ModuleApi {
    @GetMapping
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    @Operation(summary = "Get all modules", description = "This method allows you to find all modules and returns a list of ResponseModuleDTO objects.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The modules of objects were found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseModuleDTO.class))})
    })
    ResponseEntity<List<ResponseModuleDTO>> getModules();
    @GetMapping("/{name}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    @Operation(summary = "Get module by name", description = "This method allows you to find the modules by name and returns a List of ResponseModuleDTO objects.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The modules found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseModuleDTO.class))})
    })
    ResponseEntity<List<ResponseModuleDTO>> getModulesByName(@PathVariable String name);
    @GetMapping("/enseignant/{cinEnseigant}")
    ResponseEntity<List<ResponseModuleDTO>> getModulesByEnseigantCin(@PathVariable String cinEnseigant);

    @GetMapping("/moduleId/{moduleId}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    @Operation(summary = "Get module by id", description = "This method allows you to find the module by id and returns ResponseModuleDTO object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The module found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseModuleDTO.class))})
    })
    ResponseEntity<ResponseModuleDTO> getModuleById(@PathVariable int moduleId);
    @PostMapping
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    @Operation(summary = "Add module", description = "this method allows you to add a Module.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Module object created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseModuleDTO.class))})
    })
    ResponseEntity<ResponseModuleDTO> addModule(@RequestBody RequestModuleDTO moduleDTO);
    @PutMapping
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    @Operation(summary = "Update module", description = "this method allows you to update a Module.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Module object modified",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseModuleDTO.class))})
    })
    ResponseEntity<ResponseModuleDTO> updateModule(@RequestBody RequestModuleDTO moduleDTO);
    @DeleteMapping("/{name}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    @Operation(summary = "Delete module", description = "this method allows you to delete a Module.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Module object deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseModuleDTO.class))})
    })
    ResponseEntity<?> deleteModule(@PathVariable String name);

    @PostMapping("moduleIds")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
    ResponseEntity<List<ResponseModuleDTO>> getModulesByIds(@RequestBody List<Integer> moduleIds);



}
