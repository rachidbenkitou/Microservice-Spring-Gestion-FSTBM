package com.example.etudiant1service.Controller;

import com.example.etudiant1service.DTO.RequestEtudiantDTo;
import com.example.etudiant1service.DTO.ResponseEtudiantDTO;
import com.example.etudiant1service.Exceptions.EtudiantNotFoundException;
import com.example.etudiant1service.Services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;
    @GetMapping("/etudiants")
    List<ResponseEtudiantDTO> getAllEtudiants(){
        return etudiantService.getAllEtudiants();
    }
    @GetMapping("/etudiants/{id}")
    ResponseEtudiantDTO getEtudiantById(@PathVariable(name = "id") long id) throws EtudiantNotFoundException {
        return etudiantService.getEtudiantById(id);
    }
    @PostMapping("/save")
    ResponseEtudiantDTO save(@RequestBody RequestEtudiantDTo requestEtudiantDTo){
        return etudiantService.addEtudiant(requestEtudiantDTo);
    }

    @PutMapping ("/update/{id}")
    ResponseEtudiantDTO update(@PathVariable(name = "id") long id ,@RequestBody RequestEtudiantDTo requestEtudiantDTo){
        requestEtudiantDTo.setId(id);
        return etudiantService.UpdateEtudiant(requestEtudiantDTo);
    }
    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable(name = "id") long id){
        etudiantService.deleteEtudiant(id);
    }

}
