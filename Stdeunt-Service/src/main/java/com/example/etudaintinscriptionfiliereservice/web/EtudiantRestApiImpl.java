package com.example.etudaintinscriptionfiliereservice.web;


import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDto;

import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDto;

import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;
import com.example.etudaintinscriptionfiliereservice.services.EtudiantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/Etudiants")
@Tag(name = "Etudiant API", description = "API for managing Etudiant")
public class EtudiantRestApiImpl implements  EtudiantRestApi{
    private  final EtudiantService etudiantService;

    @Autowired
    public EtudiantRestApiImpl(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @Operation(summary =" Obtenir tous les étudiants", description = "Obtenir une liste de tous les étudiants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "liste de tous les étudiants",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RequestEtudiantDto.class)))}),
            @ApiResponse(responseCode = "204", description = "\n" + "Aucun étudiant trouvé ")
    })
    @Override
    public List<ResponseEtudiantDto> getAll() {
        return etudiantService.getAllEtudiants();
    }

    @Operation(summary = "\n" + "Rechercher des étudiants par ID", description = "\n" + "Rechercher d'un étudiant par ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Etudiant trouvé",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEtudiantDto.class))}),
            @ApiResponse(responseCode = "404", description = "Etudiant introuvable")
    })
    @Override
    public ResponseEtudiantDto findEtudiantById(String id) {
        return etudiantService.getEtudiantById(id);
    }


    @Operation(summary = "Trouver étudiant par apogée", description = "Trouver un étudiant par  son apogée")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Etudiant trouvé",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEtudiantDto.class))}),
            @ApiResponse(responseCode = "404", description = "Etudiant introuvable"),
            @ApiResponse(responseCode = "400", description = "Apogee étudiante invalide")
    })
    @Override
    public ResponseEtudiantDto findEtudiantByApogee(Long apogee) throws MethodArgumentNotValidException {
        return etudiantService.getEtudiantByApogee(apogee);
    }
    @Operation(summary = "Trouver étudiant par cin", description = "Trouver un étudiant par  son cin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Etudiant trouvé",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEtudiantDto.class))}),
            @ApiResponse(responseCode = "404", description = "Etudiant introuvable"),
            @ApiResponse(responseCode = "400", description = "cin étudiante invalide")
    })
    @Override
    public ResponseEtudiantDto getEtudiantByCin(String cin) {
        return etudiantService.getEtudiantByCin(cin);
    }


    @Operation(summary = "Ajouter un nouvel étudiant", description = "Ajouter un nouvel étudiant a la base de donnees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "étudiant créé",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEtudiantDto.class))}),
            @ApiResponse(responseCode = "400", description = "Données étudiantes invalides "),
            @ApiResponse(responseCode = "409", description = "étudiant  exist deja")
    })
    @Override
    public ResponseEtudiantDto saveEtudiant(@RequestBody @Valid RequestEtudiantDto requestEtudiantDto) {
        return etudiantService.addEtudiant(requestEtudiantDto);
    }

    @Operation(summary = "Modifier un étudiant ", description = "Modifier un étudiant existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'étudiant mis à jour",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEtudiantDto.class))})
    })
    @Override
    public ResponseEtudiantDto edite(
            @Parameter(required = true)
             @RequestBody @Valid RequestEtudiantDto requestEtudiantDto) {
        return etudiantService.updateEtudiant(requestEtudiantDto);
    }

    @Operation(summary = "Supprimer un étudiant", description = "Supprimer un étudiant existant par ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = " étudiant supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "\n" + "étudiant introuvable")
    })

    @Override
    public void delete(@Parameter(required = true)
             @PathVariable String id) throws EntityNotFoundException {
        etudiantService.deleteEtudiant(id);
    }
}
