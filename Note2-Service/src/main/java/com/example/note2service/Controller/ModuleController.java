package com.example.note2service.Controller;

import com.example.note2service.Controller.api.ModuleApi;
import com.example.note2service.DTO.RequestModuleDTO;
import com.example.note2service.DTO.ResponseExamenDTO;
import com.example.note2service.DTO.ResponseModuleDTO;
import com.example.note2service.Services.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ModuleController implements ModuleApi {
    private final ModuleService service;
    @Operation(summary = "Get all modules", description = "This method allows you to find all modules and returns a list of ResponseModuleDTO objects.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The modules of objects were found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseModuleDTO.class))})
    })
    @Override
    public ResponseEntity<List<ResponseModuleDTO>> getModules() {
        return new ResponseEntity<>(service.getModules(), HttpStatus.OK);
    }
    @Operation(summary = "Get module by id", description = "This method allows you to find the modules by name and returns a List of ResponseModuleDTO objects.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The modules found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseModuleDTO.class))})
    })
    @Override
    public ResponseEntity<List<ResponseModuleDTO>> getModulesByName(String name) {
        return new ResponseEntity<>(service.getModulesByName(name), HttpStatus.OK);
    }
    @Operation(summary = "Add module", description = "this method allows you to add a Module.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Module object created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseModuleDTO.class))})
    })
    @Override
    public ResponseEntity<ResponseModuleDTO> addModule(RequestModuleDTO moduleDTO) {
        return new ResponseEntity<>(service.addModule(moduleDTO), HttpStatus.CREATED);
    }
    @Operation(summary = "Update module", description = "this method allows you to update a Module.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Module object modified",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseModuleDTO.class))})
    })
    @Override
    public ResponseEntity<ResponseModuleDTO> updateModule(RequestModuleDTO moduleDTO) {
        return new ResponseEntity<>(service.updateModule(moduleDTO), HttpStatus.OK);
    }
    @Operation(summary = "Delete module", description = "this method allows you to delete a Module.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Module object deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseModuleDTO.class))})
    })
    @Override
    public ResponseEntity<?> deleteModule(String name) {
        service.deleteModuleByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
