package com.example.etudaintinscriptionfiliereservice.web;

import com.example.etudaintinscriptionfiliereservice.dtos.FiliereRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.FiliereResponseDto;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;
import com.example.etudaintinscriptionfiliereservice.services.FiliereService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/filiers")
public class FiliereRestApiImpl implements FiliereRestApi {

    private final FiliereService filiereService;

    @Autowired
    public FiliereRestApiImpl(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    @Operation(summary = "Get all filieres", description = "Get a list of all filieres.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all filieres",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FiliereResponseDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No filieres found")
    })
    @GetMapping
    public List<FiliereResponseDto> getAll() {
        return filiereService.getAll();
    }

    @Operation(summary = "Find filiere by ID", description = "Find a filiere by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filiere found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FiliereResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Filiere not found")
    })
    @GetMapping("/{id}")
    public FiliereResponseDto findFilierById(@PathVariable String id) {
        return filiereService.getFiliereById(id);
    }

    @Operation(summary = "Find filiere by name", description = "Find a filiere by its name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filiere found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FiliereResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Filiere not found"),
            @ApiResponse(responseCode = "400", description = "Invalid filiere name")
    })
    @GetMapping("/name/{name}")
    public FiliereResponseDto findFilierByName(@PathVariable String name) throws MethodArgumentNotValidException {
        return filiereService.getFiliereByNmae(name);
    }

    @Operation(summary = "Add a new filiere", description = "Add a new filiere to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Filiere created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FiliereResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid filiere data"),
            @ApiResponse(responseCode = "409", description = "Filiere already exists")
    })
    @PostMapping
    public FiliereResponseDto add(@RequestBody @Valid FiliereRequestDto filiereRequestDto) {
        return filiereService.save(filiereRequestDto);
    }
    @Operation(summary = "Edit a filiere", description = "Edit an existing filiere.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The updated filiere",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FiliereResponseDto.class))})
    })
    @Override
    public FiliereResponseDto edite(
            @Parameter(description = "The filiere object to update", required = true)
            @Valid @RequestBody FiliereRequestDto filiereRequestDto) {
        return filiereService.update(filiereRequestDto);
    }

    @Operation(summary = "Delete a filiere", description = "Delete an existing filiere by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Filiere deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Filiere not found")
    })
    @Override
    public void delete(
            @Parameter(description = "The ID of the filiere to delete", required = true)
            @PathVariable String id) throws EntityNotFoundException {
        filiereService.delete(id);
    }

}
