package com.example.note2service.Services;

import com.example.note2service.DTO.RequestExamenDTO;
import com.example.note2service.DTO.ResponseExamenDTO;
import com.example.note2service.Exceptions.ExamenNotFoundException;

import java.util.List;

public interface ExamenService {
    List<ResponseExamenDTO> getAllExamens();
    ResponseExamenDTO getExamenById(long id) throws ExamenNotFoundException;
    ResponseExamenDTO addExamen(RequestExamenDTO requestExamenDTO);
    ResponseExamenDTO UpdateExamen(RequestExamenDTO requestExamenDTO);
    void  deleteExamen(long examenId);
}
