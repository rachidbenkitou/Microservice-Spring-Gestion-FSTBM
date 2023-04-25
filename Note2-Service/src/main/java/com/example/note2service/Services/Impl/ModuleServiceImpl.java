package com.example.note2service.Services.Impl;

import com.example.note2service.DTO.RequestModuleDTO;
import com.example.note2service.DTO.ResponseModuleDTO;
import com.example.note2service.Services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ModuleServiceImpl implements ModuleService {
    @Override
    public List<ResponseModuleDTO> getModules() {
        return null;
    }

    @Override
    public List<ResponseModuleDTO> getModulesByName(String moduleName) {
        return null;
    }

    @Override
    public ResponseModuleDTO addModule(RequestModuleDTO moduleDTO) {
        return null;
    }

    @Override
    public ResponseModuleDTO updateModule(RequestModuleDTO moduleDTO) {
        return null;
    }

    @Override
    public void deleteModuleByName(String moduleName) {

    }
}
