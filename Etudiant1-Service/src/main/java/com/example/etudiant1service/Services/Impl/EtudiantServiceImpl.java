package com.example.etudiant1service.Services.Impl;

import com.example.etudiant1service.DAO.EtudiantDAO;
import com.example.etudiant1service.DTO.RequestEtudiantDTo;
import com.example.etudiant1service.DTO.ResponseEtudiantDTO;
import com.example.etudiant1service.Entities.Etudiant;
import com.example.etudiant1service.Exceptions.EtudiantNotFoundException;
import com.example.etudiant1service.Mappers.EtudiantMapper;
import com.example.etudiant1service.Services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EtudiantServiceImpl implements EtudiantService {
    @Autowired
    private EtudiantDAO etudiantDAO;
   @Autowired
    private EtudiantMapper etudiantMapper;

    @Override
    public List<ResponseEtudiantDTO> getAllEtudiants() {
        List<Etudiant> etudiantList =etudiantDAO.findAll();
        return etudiantMapper.modelToDtos(etudiantList);
    }

    @Override
    public ResponseEtudiantDTO getEtudiantById(long id) throws EtudiantNotFoundException {
        Optional<Etudiant> etudiant =  etudiantDAO.findById(id);
        if (!etudiant.isPresent()){
            throw new EtudiantNotFoundException("Il n' y a acun etudiant avec ce ID");
        }
        return etudiantMapper.modelToDto(etudiant.get());
    }

    @Override
    public ResponseEtudiantDTO addEtudiant(RequestEtudiantDTo requestEtudiantDTo) {
        Etudiant etudiant = etudiantMapper.dtoToModel(requestEtudiantDTo);
        Etudiant savedEtudiant = etudiantDAO.save(etudiant);
        return etudiantMapper.modelToDto(savedEtudiant);
    }

    @Override
    public ResponseEtudiantDTO UpdateEtudiant(RequestEtudiantDTo requestEtudiantDTo) {
        Etudiant etudiant = etudiantMapper.dtoToModel(requestEtudiantDTo);
        Etudiant savedEtudiant = etudiantDAO.save(etudiant);
        return etudiantMapper.modelToDto(savedEtudiant);
    }

    @Override
    public ResponseEtudiantDTO getEtudiantByApogee(long apogee) {
        Etudiant etudiant = etudiantDAO.findByApogee(apogee);
        return etudiantMapper.modelToDto(etudiant);
    }

    @Override
    public void deleteEtudiant(long etudiantId) {
        etudiantDAO.deleteById(etudiantId);
    }
}
