package com.example.etudiant1service.Services.Impl;

import com.example.etudiant1service.DAO.EnseigantDAO;
import com.example.etudiant1service.DTO.RequestEnseigantDTo;
import com.example.etudiant1service.DTO.ResponseEnseigantDTO;
import com.example.etudiant1service.Entities.Enseigant;
import com.example.etudiant1service.Exceptions.EnseigantNotFoundException;
import com.example.etudiant1service.Mappers.EnseigantMapper;
import com.example.etudiant1service.Services.EnseigantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EnseigantServiceImpl implements EnseigantService {
    @Autowired
    private EnseigantDAO enseigantDAO;
   @Autowired
    private EnseigantMapper enseigantMapper;

    @Override
    public List<ResponseEnseigantDTO> getAllEtudiants() {
        List<Enseigant> enseigantList = enseigantDAO.findAll();
        return enseigantMapper.modelToDtos(enseigantList);
    }

    @Override
    public ResponseEnseigantDTO getEtudiantById(long id) throws EnseigantNotFoundException {
        Optional<Enseigant> etudiant =  enseigantDAO.findById(id);
        if (!etudiant.isPresent()){
            throw new EnseigantNotFoundException("Il n' y a acun etudiant avec ce ID");
        }
        return enseigantMapper.modelToDto(etudiant.get());
    }

    @Override
    public ResponseEnseigantDTO addEtudiant(RequestEnseigantDTo requestEnseigantDTo) {
        Enseigant enseigant = enseigantMapper.dtoToModel(requestEnseigantDTo);
        Enseigant savedEnseigant = enseigantDAO.save(enseigant);
        return enseigantMapper.modelToDto(savedEnseigant);
    }

    @Override
    public ResponseEnseigantDTO UpdateEtudiant(RequestEnseigantDTo requestEnseigantDTo) {
        Enseigant enseigant = enseigantMapper.dtoToModel(requestEnseigantDTo);
        Enseigant savedEnseigant = enseigantDAO.save(enseigant);
        return enseigantMapper.modelToDto(savedEnseigant);
    }

    @Override
    public void deleteEtudiant(long etudiantId) {
        enseigantDAO.deleteById(etudiantId);
    }
}
