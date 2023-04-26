package com.example.etudiant1service.Controller;

import com.example.etudiant1service.DTO.RequestEtudiantDTo;
import com.example.etudiant1service.DTO.ResponseEtudiantDTO;
import com.example.etudiant1service.Exceptions.EtudiantNotFoundException;
import com.example.etudiant1service.Services.EtudiantService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("etudiants")
@RestControllerAdvice
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;

    @GetMapping
    List<ResponseEtudiantDTO> getAllEtudiants(){
        return etudiantService.getAllEtudiants();
    }
    @GetMapping("/{id}")
    ResponseEtudiantDTO getEtudiantById(@PathVariable(name = "id") long id) throws EtudiantNotFoundException {
        return etudiantService.getEtudiantById(id);
    }
    @GetMapping("/apogee/{apogee}")
    ResponseEtudiantDTO getEtudiantByApogee(@PathVariable(name = "apogee") long apogee)  {
        return etudiantService.getEtudiantByApogee(apogee);
    }
    @PostMapping
    ResponseEtudiantDTO save(@RequestBody RequestEtudiantDTo requestEtudiantDTo){
        return etudiantService.addEtudiant(requestEtudiantDTo);
    }

    @PutMapping ("/{id}")
    ResponseEtudiantDTO update(@PathVariable(name = "id") long id ,@RequestBody RequestEtudiantDTo requestEtudiantDTo){
        requestEtudiantDTo.setId(id);
        return etudiantService.UpdateEtudiant(requestEtudiantDTo);
    }
    @DeleteMapping("/{id}")
    void delete(@PathVariable(name = "id") long id){
        etudiantService.deleteEtudiant(id);
    }

}
