package com.example.etudaintinscriptionfiliereservice.services;

import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDto;
import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDto;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityAlreadyExistException;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;

import java.util.List;

public interface EtudiantService {


    List<ResponseEtudiantDto> getAllEtudiants();
    ResponseEtudiantDto getEtudiantById(String id) throws EntityNotFoundException ;
    ResponseEtudiantDto getEtudiantByApogee(Long apogee);
    ResponseEtudiantDto addEtudiant(RequestEtudiantDto requestEtudiantDTo)throws EntityAlreadyExistException;
    ResponseEtudiantDto updateEtudiant(RequestEtudiantDto requestEtudiantDTo);
    void  deleteEtudiant(String etudiantId)throws EntityNotFoundException;

}

