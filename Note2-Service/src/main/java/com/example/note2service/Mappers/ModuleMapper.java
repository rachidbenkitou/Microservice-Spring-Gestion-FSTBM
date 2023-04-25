package com.example.note2service.Mappers;

import com.example.note2service.DTO.RequestModuleDTO;
import com.example.note2service.DTO.ResponseModuleDTO;
import com.example.note2service.Entities.Module;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
    ResponseModuleDTO modelToDto(Module module);
    List<ResponseModuleDTO> modelToDtos(List<Module> modules);
    Module dtoToModule(RequestModuleDTO requestModuleDTO);
}
