package com.example.note2service.Controller;

import com.example.note2service.DTO.RequestExamenDTO;
import com.example.note2service.DTO.ResponseExamenDTO;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import com.example.note2service.Services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("examens")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @GetMapping("/examens")
    List<ResponseExamenDTO> getAllEtudiants(){
        return examenService.getAllExamens();
    }
    @GetMapping
    ResponseExamenDTO getEtudiantById(@PathVariable(name = "id") long id) throws ExamenNotFoundException {
        return examenService.getExamenById(id);
    }
    @PostMapping
    ResponseExamenDTO save(@RequestBody RequestExamenDTO requestExamenDTO){
        return examenService.addExamen(requestExamenDTO);
    }

    @PutMapping ("/{id}")
    ResponseExamenDTO update(@PathVariable(name = "id") long id ,@RequestBody RequestExamenDTO requestExamenDTO){
        requestExamenDTO.setId(id);
        return examenService.UpdateExamen(requestExamenDTO);
    }
    @DeleteMapping("/{id}")
    void delete(@PathVariable(name = "id") long id){
        examenService.deleteExamen(id);
    }
}
