package com.prjt.noteservice.Services.Impl;

import com.prjt.noteservice.DAO.ExamenDAO;
import com.prjt.noteservice.DTO.RequestExamenDTO;
import com.prjt.noteservice.DTO.ResponseExamenDTO;
import com.prjt.noteservice.Entities.Examen;
import com.prjt.noteservice.Exceptions.ExamenNotFoundException;
import com.prjt.noteservice.Mappers.ExamenMapper;
import com.prjt.noteservice.Services.ExamenService;
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
