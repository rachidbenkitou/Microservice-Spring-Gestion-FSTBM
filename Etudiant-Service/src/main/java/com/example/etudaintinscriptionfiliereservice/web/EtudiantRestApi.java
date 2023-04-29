package com.example.etudaintinscriptionfiliereservice.web;

import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDto;
import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDto;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EtudiantRestApi {
    @GetMapping
    List<ResponseEtudiantDto> getAll();
    @GetMapping(path = "/id/{id}")
    ResponseEtudiantDto findEtudiantById(@PathVariable String id);
    @GetMapping(path = "/apogee/{apogee}")
    ResponseEtudiantDto findEtudiantByApogee(@PathVariable Long apogee) throws MethodArgumentNotValidException;
    @PostMapping
    ResponseEtudiantDto saveEtudiant(@RequestBody RequestEtudiantDto requestEtudiantDto);
    @PutMapping
    ResponseEtudiantDto edite(@RequestBody RequestEtudiantDto requestEtudiantDto);
    @DeleteMapping(path="/{id}")
    void delete(@PathVariable String id);

}
