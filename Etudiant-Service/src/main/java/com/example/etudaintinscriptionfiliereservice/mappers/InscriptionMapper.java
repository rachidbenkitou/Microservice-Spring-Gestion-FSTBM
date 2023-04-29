package com.example.etudaintinscriptionfiliereservice.mappers;

import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionResponseDto;
import com.example.etudaintinscriptionfiliereservice.entities.Inscription;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InscriptionMapper {
    InscriptionResponseDto fromModel(Inscription inscription);
    List<InscriptionResponseDto> fromModels(List<Inscription> inscriptions);
    Inscription toModel(InscriptionRequestDto inscriptionRequestDto);
}
