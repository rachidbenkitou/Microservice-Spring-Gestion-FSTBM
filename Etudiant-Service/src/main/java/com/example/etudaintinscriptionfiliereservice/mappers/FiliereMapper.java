package com.example.etudaintinscriptionfiliereservice.mappers;

import com.example.etudaintinscriptionfiliereservice.dtos.FiliereRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.FiliereResponseDto;
import com.example.etudaintinscriptionfiliereservice.entities.Filiere;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FiliereMapper {
    FiliereResponseDto fromModel(Filiere filiere);
    List<FiliereResponseDto> fromModels(List<Filiere> filiere);
    Filiere toModel(FiliereRequestDto filiereRequestDto);
}
