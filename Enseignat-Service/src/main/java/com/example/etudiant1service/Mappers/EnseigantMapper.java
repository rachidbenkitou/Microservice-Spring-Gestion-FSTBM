package com.example.etudiant1service.Mappers;

import com.example.etudiant1service.DTO.RequestEnseigantDTo;
import com.example.etudiant1service.DTO.ResponseEnseigantDTO;
import com.example.etudiant1service.Entities.Enseigant;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface EnseigantMapper {
    Enseigant dtoToModel(RequestEnseigantDTo requestEnseigantDTo);

    ResponseEnseigantDTO modelToDto(Enseigant enseigant);

    List<ResponseEnseigantDTO> modelToDtos(List<Enseigant> enseigants);
}
