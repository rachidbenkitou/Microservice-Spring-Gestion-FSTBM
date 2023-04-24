package com.weCouldCode.fstBmManagement.noteService.Mappers;

import com.weCouldCode.fstBmManagement.noteService.DTO.RequestExamenDTO;
import com.weCouldCode.fstBmManagement.noteService.DTO.ResponseExamenDTO;
import com.weCouldCode.fstBmManagement.noteService.Entities.Examen;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ExamenMapper {
    Examen dtoToModel(RequestExamenDTO requestExamenDTO);

    ResponseExamenDTO modelToDto(Examen examen);

    List<ResponseExamenDTO> modelToDtos(List<Examen> examenList);
}
