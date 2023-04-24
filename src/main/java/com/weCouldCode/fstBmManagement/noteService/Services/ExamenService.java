package com.weCouldCode.fstBmManagement.noteService.Services;

import com.weCouldCode.fstBmManagement.noteService.DTO.RequestExamenDTO;
import com.weCouldCode.fstBmManagement.noteService.Exceptions.ExamenNotFoundException;
import com.weCouldCode.fstBmManagement.noteService.DTO.ResponseExamenDTO;

import java.util.List;

public interface ExamenService {
    List<ResponseExamenDTO> getAllExamens();
    ResponseExamenDTO getExamenById(long id) throws ExamenNotFoundException;
    ResponseExamenDTO addExamen(RequestExamenDTO requestExamenDTO);
    ResponseExamenDTO UpdateExamen(RequestExamenDTO requestExamenDTO);
    void  deleteExamen(long examenId);
}
