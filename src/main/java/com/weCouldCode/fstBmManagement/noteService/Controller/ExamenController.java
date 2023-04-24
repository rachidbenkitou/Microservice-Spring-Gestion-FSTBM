package com.weCouldCode.fstBmManagement.noteService.Controller;
import com.weCouldCode.fstBmManagement.noteService.DTO.RequestExamenDTO;
import com.weCouldCode.fstBmManagement.noteService.Exceptions.ExamenNotFoundException;
import com.weCouldCode.fstBmManagement.noteService.DTO.ResponseExamenDTO;
import com.weCouldCode.fstBmManagement.noteService.Services.ExamenService;
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
