package com.example.note2service.Controller;

import com.example.note2service.Controller.api.ModuleApi;
import com.example.note2service.DTO.RequestModuleDTO;
import com.example.note2service.DTO.ResponseModuleDTO;
import com.example.note2service.Services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
