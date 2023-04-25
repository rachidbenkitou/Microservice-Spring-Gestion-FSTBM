package com.example.etudiant1service.Services;

import com.example.etudiant1service.DTO.RequestEnseigantDTo;
import com.example.etudiant1service.DTO.ResponseEnseigantDTO;
import com.example.etudiant1service.Exceptions.EnseigantNotFoundException;

import java.util.List;

public interface EnseigantService {
    List<ResponseEnseigantDTO> getAllEtudiants();
    ResponseEnseigantDTO getEtudiantById(long id) throws EnseigantNotFoundException;
    ResponseEnseigantDTO addEtudiant(RequestEnseigantDTo requestEnseigantDTo);
    ResponseEnseigantDTO UpdateEtudiant(RequestEnseigantDTo requestEnseigantDTo);
    void  deleteEtudiant(long etudiantId);
}
