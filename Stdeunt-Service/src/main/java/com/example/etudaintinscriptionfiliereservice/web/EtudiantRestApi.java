package com.example.etudaintinscriptionfiliereservice.web;

import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDto;
import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EtudiantRestApi {
    @GetMapping
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_TEACHER')")
    List<ResponseEtudiantDto> getAll();
    @GetMapping(path = "/id/{id}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_TEACHER')")
    ResponseEtudiantDto findEtudiantById(@PathVariable String id);
    @GetMapping("/filiere/{filiereId}")
    public List<ResponseEtudiantDto> getEtudiantsByFiliereId(@PathVariable String filiereId);
    @GetMapping(path = "/apogee/{apogee}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_TEACHER')")
    ResponseEtudiantDto findEtudiantByApogee(@PathVariable Long apogee) throws MethodArgumentNotValidException;
    @GetMapping(path = "/cin/{cin}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_TEACHER')")
    ResponseEtudiantDto getEtudiantByCin(@PathVariable String cin);
    @PostMapping
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    ResponseEtudiantDto saveEtudiant(@RequestBody RequestEtudiantDto requestEtudiantDto);
    @PutMapping
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    ResponseEtudiantDto edite(@RequestBody RequestEtudiantDto requestEtudiantDto);
    @DeleteMapping(path="/{id}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    void delete(@PathVariable String id);

}
