package com.weCouldCode.fstBmManagement.noteService.Services.Impl;

import com.weCouldCode.fstBmManagement.noteService.DTO.RequestExamenDTO;
import com.weCouldCode.fstBmManagement.noteService.Entities.Examen;
import com.weCouldCode.fstBmManagement.noteService.Exceptions.ExamenNotFoundException;
import com.weCouldCode.fstBmManagement.noteService.Mappers.ExamenMapper;
import com.weCouldCode.fstBmManagement.noteService.Services.ExamenService;
import com.weCouldCode.fstBmManagement.noteService.DAO.ExamenDAO;
import com.weCouldCode.fstBmManagement.noteService.DTO.ResponseExamenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    private ExamenDAO examenDAO;
    @Autowired
    private ExamenMapper examenMapper;
    @Override
    public List<ResponseExamenDTO> getAllExamens() {
        List<Examen> examenList =examenDAO.findAll();
        return examenMapper.modelToDtos(examenList);
    }

    @Override
    public ResponseExamenDTO getExamenById(long id) throws ExamenNotFoundException {
        Optional<Examen> examen =  examenDAO.findById(id);
        if (!examen.isPresent()){
            throw new ExamenNotFoundException("Il n' y a acun etudiant avec ce ID");
        }
        return examenMapper.modelToDto(examen.get());
    }

    @Override
    public ResponseExamenDTO addExamen(RequestExamenDTO requestExamenDTO) {
       Examen examen = examenMapper.dtoToModel(requestExamenDTO);
       Examen savedExamen = examenDAO.save(examen);
        return examenMapper.modelToDto(savedExamen);
    }

    @Override
    public ResponseExamenDTO UpdateExamen(RequestExamenDTO requestExamenDTO) {
        Examen examen = examenMapper.dtoToModel(requestExamenDTO);
        Examen savedExamen = examenDAO.save(examen);
        return examenMapper.modelToDto(savedExamen);
    }

    @Override
    public void deleteExamen(long etudiantId) {
        examenDAO.deleteById(etudiantId);
    }
}
