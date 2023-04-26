package com.example.etudiant1service.Services;

import com.example.etudiant1service.DTO.RequestEtudiantDTo;
import com.example.etudiant1service.DTO.ResponseEtudiantDTO;
import com.example.etudiant1service.Exceptions.EtudiantNotFoundException;

import java.util.List;

public interface EtudiantService {
    List<ResponseEtudiantDTO> getAllEtudiants();
    ResponseEtudiantDTO getEtudiantById(long id) throws EtudiantNotFoundException;
    ResponseEtudiantDTO addEtudiant(RequestEtudiantDTo requestEtudiantDTo);
    ResponseEtudiantDTO UpdateEtudiant(RequestEtudiantDTo requestEtudiantDTo);
    ResponseEtudiantDTO getEtudiantByApogee(long apogee);
    void  deleteEtudiant(long etudiantId);
}
