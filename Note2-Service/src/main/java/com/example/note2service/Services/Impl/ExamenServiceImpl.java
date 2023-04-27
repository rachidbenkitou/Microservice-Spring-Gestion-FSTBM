package com.example.note2service.Services.Impl;

import com.example.note2service.DAO.ExamenDAO;
import com.example.note2service.DAO.ModuleDAO;
import com.example.note2service.DTO.RequestExamenDTO;
import com.example.note2service.DTO.ResponseExamenDTO;
import com.example.note2service.Entities.Examen;
import com.example.note2service.Entities.Note;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import com.example.note2service.Mappers.ExamenMapper;
import com.example.note2service.Services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * This class provides the implementation of methods to interact with the ExamenDAO to perform CRUD operations on Examen objects.
 */
@Service
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    private ExamenDAO examenDAO;
    @Autowired
    private ExamenMapper examenMapper;
    @Autowired
    private ModuleDAO moduleDAO;
    /**
     * Retrieves all Examens from the database.
     *
     * @return a List of ResponseExamenDTO objects representing all Examens in the database.
     */
    @Override
    public List<ResponseExamenDTO> getAllExamens() {
        List<Examen> examenList =examenDAO.findAll();

        return examenMapper.modelToDtos(examenList);
    }

    /**
     * Retrieves an Examen from the database by ID.
     *
     * @param id the ID of the Examen to retrieve.
     * @return a ResponseExamenDTO object representing the Examen with the specified ID.
     * @throws ExamenNotFoundException if no Examen is found with the specified ID.
     */
    @Override
    public ResponseExamenDTO getExamenById(long id) throws ExamenNotFoundException {
        Optional<Examen> examen =  examenDAO.findById(id);
        if (!examen.isPresent()){
            throw new ExamenNotFoundException("Il n' y a acun etudiant avec ce ID");
        }
        return examenMapper.modelToDto(examen.get());
    }
    /**
     * Adds an Examen to the database.
     *
     * @param requestExamenDTO a RequestExamenDTO object representing the Examen to add.
     * @return a ResponseExamenDTO object representing the added Examen.
     */
    @Override
    public ResponseExamenDTO addExamen(RequestExamenDTO requestExamenDTO) {
       Examen examen = examenMapper.dtoToModel(requestExamenDTO);
       Examen savedExamen = examenDAO.save(examen);
        return examenMapper.modelToDto(savedExamen);
    }
    /**
     * Updates an Examen in the database.
     *
     * @param requestExamenDTO a RequestExamenDTO object representing the Examen to update.
     * @return a ResponseExamenDTO object representing the updated Examen.
     */
    @Override
    public ResponseExamenDTO UpdateExamen(RequestExamenDTO requestExamenDTO) {
        Examen examen = examenMapper.dtoToModel(requestExamenDTO);
        Examen savedExamen = examenDAO.save(examen);
        return examenMapper.modelToDto(savedExamen);
    }
    /**
     * Deletes an Examen from the database.
     *
     * @param etudiantId the ID of the Examen to delete.
     */
    @Override
    public void deleteExamen(long etudiantId) {
        examenDAO.deleteById(etudiantId);
    }
}
