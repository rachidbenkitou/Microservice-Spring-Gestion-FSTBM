package com.example.note2service.Services.Impl;

import com.example.note2service.DAO.ModuleDAO;
import com.example.note2service.DTO.RequestModuleDTO;
import com.example.note2service.DTO.ResponseModuleDTO;
import com.example.note2service.Entities.Module;
import com.example.note2service.Exceptions.module.ModuleAlreadyExistsException;
import com.example.note2service.Exceptions.module.ModuleListIsEmptyException;
import com.example.note2service.Exceptions.module.ModuleRequestIsNull;
import com.example.note2service.Mappers.ModuleMapper;
import com.example.note2service.Services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ModuleServiceImpl implements ModuleService {
    private  final ModuleDAO dao;
    private final ModuleMapper mapper;
    @Override
    public List<ResponseModuleDTO> getModules() {
        return listOfResponseModuleDTOS(dao.findAll());
    }
    @Override
    public List<ResponseModuleDTO> getModulesByName(String moduleName) {
        return listOfResponseModuleDTOS(dao.findModulesByModuleNameLikeIgnoreCase(moduleName));
    }
    private List<ResponseModuleDTO> listOfResponseModuleDTOS(List<Module> modules){
        List<ResponseModuleDTO> responseModuleDTOS=mapper.modelToDtos(modules);
        if(responseModuleDTOS.isEmpty()) throw  new ModuleListIsEmptyException("The list is empty");
        return responseModuleDTOS;
    }
    @Override
    public ResponseModuleDTO addModule(RequestModuleDTO moduleDTO) {
        if (dao.existsByModuleName(moduleDTO.getModuleName()))
            throw  new ModuleAlreadyExistsException(String.format("The module with name %s is already exists",moduleDTO.getModuleName()));
        return EntityResponseModuleDto(moduleDTO);
    }
    @Override
    public ResponseModuleDTO updateModule(RequestModuleDTO moduleDTO) {
        return EntityResponseModuleDto(moduleDTO);
    }
    public ResponseModuleDTO EntityResponseModuleDto(RequestModuleDTO moduleDTO){
        ResponseModuleDTO responseModuleDTO=mapper.modelToDto(dao.save(mapper.dtoToModule(moduleDTO)));
        if (responseModuleDTO==null) throw new ModuleRequestIsNull("The request is null, is doesn't contain any content");
        return  responseModuleDTO;
    }
    @Override
    public void deleteModuleByName(String moduleName) {
        dao.deleteByModuleName(moduleName);
    }
}
