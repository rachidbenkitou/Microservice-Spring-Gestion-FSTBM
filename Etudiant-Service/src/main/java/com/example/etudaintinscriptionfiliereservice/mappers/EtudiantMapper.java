package com.example.etudaintinscriptionfiliereservice.mappers;

import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDto;
import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDto;

import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EtudiantMapper {
    ResponseEtudiantDto fromModel(Etudiant etudiant );
    List<ResponseEtudiantDto> fromModels(List<Etudiant> etudiant );
    Etudiant toModel(RequestEtudiantDto requestEtudiantDTo);
}
