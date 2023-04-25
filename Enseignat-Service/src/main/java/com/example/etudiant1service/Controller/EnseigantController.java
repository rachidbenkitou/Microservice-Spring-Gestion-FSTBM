package com.example.etudiant1service.Controller;

import com.example.etudiant1service.DTO.RequestEnseigantDTo;
import com.example.etudiant1service.DTO.ResponseEnseigantDTO;
import com.example.etudiant1service.Exceptions.EnseigantNotFoundException;
import com.example.etudiant1service.Services.EnseigantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class EnseigantController {
    @Autowired
    private EnseigantService enseigantService;
    @GetMapping("/enseignats")
    List<ResponseEnseigantDTO> getAllEtudiants(){
        return enseigantService.getAllEtudiants();
    }
    @GetMapping("/enseignats/{id}")
    ResponseEnseigantDTO getEtudiantById(@PathVariable(name = "id") long id) throws EnseigantNotFoundException {
        return enseigantService.getEtudiantById(id);
    }
    @PostMapping("/save")
    ResponseEnseigantDTO save(@RequestBody RequestEnseigantDTo requestEnseigantDTo){
        return enseigantService.addEtudiant(requestEnseigantDTo);
    }

    @PutMapping ("/update/{id}")
    ResponseEnseigantDTO update(@PathVariable(name = "id") long id , @RequestBody RequestEnseigantDTo requestEnseigantDTo){
        requestEnseigantDTo.setId(id);
        return enseigantService.UpdateEtudiant(requestEnseigantDTo);
    }
    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable(name = "id") long id){
        enseigantService.deleteEtudiant(id);
    }

}
