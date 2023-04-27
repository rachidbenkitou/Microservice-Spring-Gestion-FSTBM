package com.example.etudaintinscriptionfiliereservice.services;

import com.example.etudaintinscriptionfiliereservice.dtos.FiliereRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.FiliereResponseDto;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityAlreadyExistException;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public interface FiliereService {
    List<FiliereResponseDto> getAll();
    FiliereResponseDto getFiliereById(String idFiliere)throws EntityNotFoundException;
    FiliereResponseDto getFiliereByNmae(String name)throws EntityNotFoundException, MethodArgumentNotValidException;
    FiliereResponseDto save(FiliereRequestDto filiereRequestDto)throws EntityAlreadyExistException;
    FiliereResponseDto update(FiliereRequestDto filiereRequestDto);
    void delete(String id)throws EntityNotFoundException;

}
