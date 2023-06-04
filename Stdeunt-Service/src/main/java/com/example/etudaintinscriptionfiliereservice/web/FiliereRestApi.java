package com.example.etudaintinscriptionfiliereservice.web;

import com.example.etudaintinscriptionfiliereservice.dtos.FiliereRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.FiliereResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface FiliereRestApi {
    @GetMapping
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    List<FiliereResponseDto> getAll();
    @GetMapping(path = "/id/{id}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_TEACHER')")
    FiliereResponseDto findFilierById(@PathVariable String id);
    @GetMapping(path = "/name/{name}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_TEACHER')")
    FiliereResponseDto findFilierByName(@PathVariable String name) throws MethodArgumentNotValidException;
    @PostMapping
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    FiliereResponseDto add(@RequestBody FiliereRequestDto filiereRequestDto);
    @PutMapping
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    FiliereResponseDto edite(@RequestBody FiliereRequestDto filiereRequestDto);
    @DeleteMapping(path="/{id}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    void delete(@PathVariable String id);
}
