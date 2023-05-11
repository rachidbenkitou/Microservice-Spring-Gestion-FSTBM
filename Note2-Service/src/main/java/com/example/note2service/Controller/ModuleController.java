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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ModuleController implements ModuleApi {
    private final ModuleService service;

    @Override
    public ResponseEntity<List<ResponseModuleDTO>> getModules() {
        return new ResponseEntity<>(service.getModules(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ResponseModuleDTO>> getModulesByName(String name) {
        return new ResponseEntity<>(service.getModulesByName(name), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ResponseModuleDTO> getModuleById(int moduleId) {
        return new ResponseEntity<>(service.getModuleById(moduleId), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ResponseModuleDTO> addModule(RequestModuleDTO moduleDTO) {
        return new ResponseEntity<>(service.addModule(moduleDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseModuleDTO> updateModule(RequestModuleDTO moduleDTO) {
        return new ResponseEntity<>(service.updateModule(moduleDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteModule(String name) {
        service.deleteModuleByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
