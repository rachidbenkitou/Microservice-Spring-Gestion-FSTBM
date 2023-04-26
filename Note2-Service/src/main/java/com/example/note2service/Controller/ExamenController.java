package com.example.note2service.Controller;

import com.example.note2service.DTO.RequestExamenDTO;
import com.example.note2service.DTO.ResponseExamenDTO;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import com.example.note2service.Services.ExamenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("examens")
@RestControllerAdvice
public class ExamenController {

    @Autowired
    private ExamenService examenService;
    @Operation(summary = "Get all examens", description = "This method allows you to find all examens and returns a list of ResponseExamenDTO objects.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Examens of objects were found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseExamenDTO.class))})
    })
    @GetMapping
    List<ResponseExamenDTO> getAllEtudiants(){
        return examenService.getAllExamens();
    }
    @Operation(summary = "Get examen by id", description = "This method allows you to find the Examens by Id and returns a  ResponseExamenDTO object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Examen found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseExamenDTO.class))})
    })
    @GetMapping("/{id}")
    ResponseExamenDTO getEtudiantById(@PathVariable(name = "id") long id) throws ExamenNotFoundException {
        return examenService.getExamenById(id);
    }
    @Operation(summary = "Add examen", description = "this method allows you to add an Examen.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Examen object created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseExamenDTO.class))})
    })
    @PostMapping
    ResponseExamenDTO save(@RequestBody RequestExamenDTO requestExamenDTO){
        return examenService.addExamen(requestExamenDTO);
    }
    @Operation(summary = "Update examen", description = "this method allows you to Update an Examen.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Examen object modified",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseExamenDTO.class))})
    })
    @PutMapping ("/{id}")
    ResponseExamenDTO update(@PathVariable(name = "id") long id ,@RequestBody RequestExamenDTO requestExamenDTO){
        requestExamenDTO.setId(id);
        return examenService.UpdateExamen(requestExamenDTO);
    }

    @Operation(summary = "Delete examen", description = "this method allows you to delete an Examen by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Examen object deleted",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/{id}")
    void delete(@PathVariable(name = "id") long id){
        examenService.deleteExamen(id);
    }
}
