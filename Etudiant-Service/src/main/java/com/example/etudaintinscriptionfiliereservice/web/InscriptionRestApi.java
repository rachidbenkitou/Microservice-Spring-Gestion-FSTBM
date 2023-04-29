package com.example.etudaintinscriptionfiliereservice.web;

import com.example.etudaintinscriptionfiliereservice.dtos.FiliereRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.FiliereResponseDto;
import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionResponseDto;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface InscriptionRestApi {
    @GetMapping
    List<InscriptionResponseDto> getAll();
    @GetMapping(path = "/{id}")
    InscriptionResponseDto findInscriptionById(@PathVariable String id);
    @PostMapping
    InscriptionResponseDto add(@RequestBody InscriptionRequestDto inscriptionRequestDto);
    @PutMapping
    InscriptionResponseDto edite(@RequestBody InscriptionRequestDto inscriptionRequestDto);
    @DeleteMapping(path="/{id}")
    void delete(@PathVariable String id);
}
