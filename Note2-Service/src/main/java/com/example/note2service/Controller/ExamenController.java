package com.example.note2service.Controller;

import com.example.note2service.Controller.api.ExamenApi;
import com.example.note2service.DTO.RequestExamenDTO;
import com.example.note2service.DTO.ResponseExamenDTO;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import com.example.note2service.Services.ExamenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RestControllerAdvice
@RequiredArgsConstructor
public class ExamenController implements ExamenApi {

    private final ExamenService examenService;
    @Override
    public List<ResponseExamenDTO> getAllEtudiants(){
        return examenService.getAllExamens();
    }
    @Override
    public ResponseExamenDTO getEtudiantById(@PathVariable(name = "id") long id) throws ExamenNotFoundException {
        return examenService.getExamenById(id);
    }
    @Override
    public ResponseExamenDTO save(@RequestBody RequestExamenDTO requestExamenDTO){
        return examenService.addExamen(requestExamenDTO);
    }
    @Override
    public ResponseExamenDTO update(@PathVariable(name = "id") long id ,@RequestBody RequestExamenDTO requestExamenDTO){
        requestExamenDTO.setId(id);
        return examenService.UpdateExamen(requestExamenDTO);
    }
    @Override
    public void delete(@PathVariable(name = "id") long id){
        examenService.deleteExamen(id);
    }
}
