package com.prjt.noteservice.Mappers;

import com.prjt.noteservice.DTO.RequestExamenDTO;
import com.prjt.noteservice.DTO.ResponseExamenDTO;
import com.prjt.noteservice.Entities.Examen;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ExamenMapper {
    Examen dtoToModel(RequestExamenDTO requestExamenDTO);

    ResponseExamenDTO modelToDto(Examen examen);

    List<ResponseExamenDTO> modelToDtos(List<Examen> examenList);
}
