package com.example.note2service.Controller.api;

import com.example.note2service.DTO.RequestModuleDTO;
import com.example.note2service.DTO.ResponseModuleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/modules")
public interface ModuleApi {
    @GetMapping
    ResponseEntity<List<ResponseModuleDTO>> getModules();
    @GetMapping("/{name}")
    ResponseEntity<List<ResponseModuleDTO>> getModulesByName(@PathVariable String name);
    @PostMapping
    ResponseEntity<ResponseModuleDTO> addModule(@RequestBody RequestModuleDTO moduleDTO);
    @PutMapping
    ResponseEntity<ResponseModuleDTO> updateModule(@RequestBody RequestModuleDTO moduleDTO);
    @DeleteMapping("/{name}")
    ResponseEntity<?> deleteModule(@PathVariable String name);



}
