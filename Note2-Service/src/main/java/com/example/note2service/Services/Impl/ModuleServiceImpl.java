package com.example.note2service.Services.Impl;

import com.example.note2service.DAO.ModuleDAO;
import com.example.note2service.DTO.RequestModuleDTO;
import com.example.note2service.DTO.ResponseModuleDTO;
import com.example.note2service.Entities.Module;
import com.example.note2service.Exceptions.ModuleNotFoundException;
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
    /**
     * Retrieves a list of all modules and maps them to a list of ResponseModuleDTO objects.
     *
     * @return a list of ResponseModuleDTO objects
     */
    @Override
    public List<ResponseModuleDTO> getModules() {
        return listOfResponseModuleDTOS(dao.findAll());
    }
    /**
     * Retrieves a list of modules by name and maps them to a list of ResponseModuleDTO objects.
     *
     * @param moduleName the name of the module to search for
     * @return a list of ResponseModuleDTO objects
     * @throws ModuleListIsEmptyException if the list is empty
     */
    @Override
    public List<ResponseModuleDTO> getModulesByName(String moduleName) {
        return listOfResponseModuleDTOS(dao.findModulesByModuleNameLikeIgnoreCase(moduleName));
    }
    /**
     * Maps a list of Module objects to a list of ResponseModuleDTO objects.
     *
     * @param modules the list of Module objects to map
     * @return a list of ResponseModuleDTO objects
     * @throws ModuleListIsEmptyException if the list is empty
     */
    private List<ResponseModuleDTO> listOfResponseModuleDTOS(List<Module> modules){
        List<ResponseModuleDTO> responseModuleDTOS=mapper.modelToDtos(modules);
        if(responseModuleDTOS.isEmpty()) throw  new ModuleListIsEmptyException("The list is empty");
        return responseModuleDTOS;
    }
    /**
     * Adds a new module.
     *
     * @param moduleDTO the RequestModuleDTO object representing the new module
     * @return the ResponseModuleDTO object representing the added module
     * @throws ModuleAlreadyExistsException if a module with the same name already exists
     */
    @Override
    public ResponseModuleDTO addModule(RequestModuleDTO moduleDTO) {
        if (dao.existsByModuleName(moduleDTO.getModuleName()))
            throw  new ModuleAlreadyExistsException(String.format("The module with name %s is already exists",moduleDTO.getModuleName()));
        return EntityResponseModuleDto(moduleDTO);
    }
    /**
     * Updates an existing module.
     *
     * @param moduleDTO the RequestModuleDTO object representing the updated module
     * @return the ResponseModuleDTO object representing the updated module
     * @throws ModuleRequestIsNull if the RequestModuleDTO object is null
     */
    @Override
    public ResponseModuleDTO updateModule(RequestModuleDTO moduleDTO) {
        return EntityResponseModuleDto(moduleDTO);
    }
    /**
     * Maps a RequestModuleDTO object to a ResponseModuleDTO object and saves it to the database.
     *
     * @param moduleDTO the RequestModuleDTO object to map
     * @return the ResponseModuleDTO object representing the mapped module
     * @throws ModuleRequestIsNull if the RequestModuleDTO object is null
     */
    public ResponseModuleDTO EntityResponseModuleDto(RequestModuleDTO moduleDTO){
        ResponseModuleDTO responseModuleDTO=mapper.modelToDto(dao.save(mapper.dtoToModule(moduleDTO)));
        if (responseModuleDTO==null) throw new ModuleRequestIsNull("The request is null, is doesn't contain any content");
        return  responseModuleDTO;
    }
/**
 * Deletes a module by name.
 *
 * @param moduleName the name of the module to delete.

 */
    @Override
    public void deleteModuleByName(String moduleName) {
        dao.deleteByModuleName(moduleName);
    }

    @Override
    public ResponseModuleDTO getModuleById(int moduleId) {
        Module module=dao.findById(moduleId).orElseThrow(()->
                new ModuleNotFoundException(String.format("the module with id %d not exist.", moduleId)));
        return mapper.modelToDto(module);
    }

    @Override
    public List<ResponseModuleDTO> getModulesByIds(List<Integer> moduleIds) {

        System.out.println(moduleIds);
        return mapper.modelToDtos(moduleIds.stream().map((id)->dao.findById(id).orElseThrow(()->
                new ModuleNotFoundException(String.format("the module with id %d not exist.", id)))).toList());

    }
}
