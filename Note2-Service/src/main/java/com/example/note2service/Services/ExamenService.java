package com.example.note2service.Services;

import com.example.note2service.DTO.RequestExamenDTO;
import com.example.note2service.DTO.ResponseExamenDTO;
import com.example.note2service.Entities.Semestre;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ExamenService {
    List<ResponseExamenDTO> getAllExamens();
    ResponseExamenDTO getExamenById(long id) throws ExamenNotFoundException;
    ResponseExamenDTO addExamen(RequestExamenDTO requestExamenDTO);
    ResponseExamenDTO UpdateExamen(RequestExamenDTO requestExamenDTO);
    List<ResponseExamenDTO> getExamenByDate(String keyword1, Semestre keyword2);
    void  deleteExamen(long examenId);
}
