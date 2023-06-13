package com.example.etudaintinscriptionfiliereservice.mappers;

import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionResponseDto;
import com.example.etudaintinscriptionfiliereservice.entities.Inscription;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(componentModel = "spring")
@Service
public interface InscriptionMapper {
    InscriptionResponseDto fromModel(Inscription inscription);
    List<InscriptionResponseDto> fromModels(List<Inscription> inscriptions);
    Inscription toModel(InscriptionRequestDto inscriptionRequestDto);
}
