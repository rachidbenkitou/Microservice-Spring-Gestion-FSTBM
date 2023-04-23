package com.prjt.noteservice.Services;

import com.prjt.noteservice.DTO.RequestExamenDTO;
import com.prjt.noteservice.DTO.ResponseExamenDTO;
import com.prjt.noteservice.Exceptions.ExamenNotFoundException;

import java.util.List;

public interface ExamenService {
    List<ResponseExamenDTO> getAllExamens();
    ResponseExamenDTO getExamenById(long id) throws ExamenNotFoundException;
    ResponseExamenDTO addExamen(RequestExamenDTO requestExamenDTO);
    ResponseExamenDTO UpdateExamen(RequestExamenDTO requestExamenDTO);
    void  deleteExamen(long examenId);
}
