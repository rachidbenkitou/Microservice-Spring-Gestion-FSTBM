package com.example.etudaintinscriptionfiliereservice.web;

import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionResponseDto;
import com.example.etudaintinscriptionfiliereservice.services.InscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/inscriptions")
@Tag(name = "Inscription API", description = "API for managing inscriptions")
public class InscriptionRestApiImpl implements InscriptionRestApi{
    private final InscriptionService inscriptionService;

    @Autowired
    public InscriptionRestApiImpl(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @Operation(summary = "Get all inscriptions", description = "Get a list of all inscriptions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of inscriptions",
                    content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = InscriptionResponseDto.class))) }),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    public List<InscriptionResponseDto> getAll() {
        return inscriptionService.getAll();
    }

    @Operation(summary = "Find inscription by ID", description = "Find an inscription by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the inscription",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InscriptionResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Inscription not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    public InscriptionResponseDto findInscriptionById(@PathVariable String id) {
        return inscriptionService.getInscription(id);
    }

    @Operation(summary = "Add inscription", description = "Add a new inscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added the inscription",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InscriptionResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "409", description = "Inscription already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    public InscriptionResponseDto add(@RequestBody @Valid InscriptionRequestDto inscriptionRequestDto) {
        return inscriptionService.save(inscriptionRequestDto);
    }

    @Operation(summary = "Edit inscription", description = "Edit an existing inscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited the inscription",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InscriptionResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    public InscriptionResponseDto edite(@RequestBody @Valid InscriptionRequestDto inscriptionRequestDto) {
        return inscriptionService.update(inscriptionRequestDto);
    }

    @Operation(summary = "Delete inscription", description = "Delete an existing inscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the inscription"),
            @ApiResponse(responseCode = "404", description = "Inscription not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    public void delete(@PathVariable String id) {
        inscriptionService.delete(id);
    }
}

