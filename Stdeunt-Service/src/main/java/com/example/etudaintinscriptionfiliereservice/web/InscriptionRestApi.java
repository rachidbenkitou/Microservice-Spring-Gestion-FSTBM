package com.example.etudaintinscriptionfiliereservice.web;

import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface InscriptionRestApi {
    @GetMapping
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    List<InscriptionResponseDto> getAll();
    @GetMapping(path = "/{id}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    InscriptionResponseDto findInscriptionById(@PathVariable String id);
    @GetMapping(path = "/cin/{cin}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    InscriptionResponseDto getInscriptionByCin(@PathVariable String cin);
    @PostMapping
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_STUDENT')")
    InscriptionResponseDto add(@RequestBody InscriptionRequestDto inscriptionRequestDto);
    @PutMapping
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_STUDENT')")
    InscriptionResponseDto edite(@RequestBody InscriptionRequestDto inscriptionRequestDto);
    @DeleteMapping(path="/{id}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_STUDENT')")
    void delete(@PathVariable String id);
}
