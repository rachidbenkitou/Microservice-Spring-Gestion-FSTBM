package com.example.etudiant1service.Mappers;

import com.example.etudiant1service.DTO.RequestEtudiantDTo;
import com.example.etudiant1service.DTO.ResponseEtudiantDTO;
import com.example.etudiant1service.Entities.Etudiant;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface EtudiantMapper {
    Etudiant dtoToModel(RequestEtudiantDTo requestEtudiantDTo);

    ResponseEtudiantDTO modelToDto(Etudiant etudiant);

    List<ResponseEtudiantDTO> modelToDtos(List<Etudiant> etudiants);
}
