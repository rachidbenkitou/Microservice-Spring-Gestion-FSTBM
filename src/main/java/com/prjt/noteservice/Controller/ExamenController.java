package com.prjt.noteservice.Controller;

import com.prjt.noteservice.DTO.RequestExamenDTO;
import com.prjt.noteservice.DTO.ResponseExamenDTO;
import com.prjt.noteservice.Exceptions.ExamenNotFoundException;
import com.prjt.noteservice.Services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api1")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @GetMapping("/examens")
    List<ResponseExamenDTO> getAllEtudiants(){
        return examenService.getAllExamens();
    }
    @GetMapping("/examens/{id}")
    ResponseExamenDTO getEtudiantById(@PathVariable(name = "id") long id) throws ExamenNotFoundException {
        return examenService.getExamenById(id);
    }
    @PostMapping("/save")
    ResponseExamenDTO save(@RequestBody RequestExamenDTO requestExamenDTO){
        return examenService.addExamen(requestExamenDTO);
    }

    @PutMapping ("/update/{id}")
    ResponseExamenDTO update(@PathVariable(name = "id") long id ,@RequestBody RequestExamenDTO requestExamenDTO){
        requestExamenDTO.setId(id);
        return examenService.UpdateExamen(requestExamenDTO);
    }
    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable(name = "id") long id){
        examenService.deleteExamen(id);
    }
}
