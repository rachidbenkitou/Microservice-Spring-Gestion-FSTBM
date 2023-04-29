package com.example.etudaintinscriptionfiliereservice.services;

import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionResponseDto;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityAlreadyExistException;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;
import com.example.etudaintinscriptionfiliereservice.exceptions.InvalidEntityException;

import java.util.List;

public interface InscriptionService {
     List<InscriptionResponseDto> getAll();
     InscriptionResponseDto getInscription(String id)throws EntityNotFoundException;
     InscriptionResponseDto save(InscriptionRequestDto inscriptionRequestDto)throws EntityAlreadyExistException, InvalidEntityException;
     InscriptionResponseDto update(InscriptionRequestDto inscriptionRequestDto)throws InvalidEntityException;
    void delete(String id)throws EntityNotFoundException;
}
