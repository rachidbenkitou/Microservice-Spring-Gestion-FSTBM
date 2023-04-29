package com.example.etudaintinscriptionfiliereservice.web;

import com.example.etudaintinscriptionfiliereservice.dtos.FiliereRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.FiliereResponseDto;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface FiliereRestApi {
    @GetMapping
    List<FiliereResponseDto> getAll();
    @GetMapping(path = "/id/{id}")
    FiliereResponseDto findFilierById(@PathVariable String id);
    @GetMapping(path = "/name/{name}")
    FiliereResponseDto findFilierByName(@PathVariable String name) throws MethodArgumentNotValidException;
    @PostMapping
    FiliereResponseDto add(@RequestBody FiliereRequestDto filiereRequestDto);
    @PutMapping
    FiliereResponseDto edite(@RequestBody FiliereRequestDto filiereRequestDto);
    @DeleteMapping(path="/{id}")
    void delete(@PathVariable String id);
}
