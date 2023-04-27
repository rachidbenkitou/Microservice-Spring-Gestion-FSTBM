package com.example.note2service.Mappers;

import com.example.note2service.DTO.RequestExamenDTO;
import com.example.note2service.DTO.ResponseExamenDTO;
import com.example.note2service.Entities.Examen;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Service
public interface ExamenMapper {
    Examen dtoToModel(RequestExamenDTO requestExamenDTO);

    ResponseExamenDTO modelToDto(Examen examen);

    List<ResponseExamenDTO> modelToDtos(List<Examen> examenList);



}
