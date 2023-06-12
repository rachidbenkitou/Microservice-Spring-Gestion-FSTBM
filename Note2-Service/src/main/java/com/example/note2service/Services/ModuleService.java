package com.example.note2service.Services;

import com.example.note2service.DTO.RequestModuleDTO;
import com.example.note2service.DTO.ResponseModuleDTO;

import java.util.List;

public interface ModuleService {
    List<ResponseModuleDTO> getModules();
    List<ResponseModuleDTO> getModulesByName(String moduleName);
    ResponseModuleDTO addModule(RequestModuleDTO moduleDTO);
    ResponseModuleDTO updateModule(RequestModuleDTO moduleDTO);
    void deleteModuleByName(String moduleName);
    ResponseModuleDTO getModuleById(int moduleId);

    List<ResponseModuleDTO> getModulesByIds(List<Integer> moduleIds);
}
