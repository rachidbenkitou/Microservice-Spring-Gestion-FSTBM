package com.example.note2service.Services.Impl;

import com.example.note2service.DAO.ModuleDAO;
import com.example.note2service.DTO.RequestModuleDTO;
import com.example.note2service.DTO.ResponseModuleDTO;
import com.example.note2service.Entities.Module;
import com.example.note2service.Entities.Semestre;
import com.example.note2service.Exceptions.ModuleNotFoundException;
import com.example.note2service.Exceptions.module.ModuleAlreadyExistsException;
import com.example.note2service.Exceptions.module.ModuleListIsEmptyException;
import com.example.note2service.Mappers.ModuleMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ModuleServiceImplTest {
    @Mock
    private ModuleDAO moduleDAO;
    @Mock private ModuleMapper moduleMapper;
    @InjectMocks
    private ModuleServiceImpl moduleService;

    private List<Module> moduleList;
    private List<ResponseModuleDTO> responseModuleDTOS;
    private Module module2;
    private Module module1;
    private ResponseModuleDTO responseModuleDTO1;
    private RequestModuleDTO requestModuleDTO1;
    private  ResponseModuleDTO responseModuleDTO2;
    private RequestModuleDTO requestModuleDTO2;
    String moduleName;

    @BeforeEach
    void setUp() {
        moduleName="analyse";
        module1= Module.builder().moduleId(1).moduleName("analyse1").moduleSemestre(Semestre.SEMESTRE1).build();
        module2= Module.builder().moduleId(2).moduleName("analyse2").moduleSemestre(Semestre.SEMESTRE1).build();
        moduleList= new ArrayList<>();
        moduleList.add(module1);
        moduleList.add(module2);

        responseModuleDTO1= ResponseModuleDTO.builder().moduleId(1).moduleName("analyse1").moduleSemestre(Semestre.SEMESTRE1).build();
        requestModuleDTO1= RequestModuleDTO.builder().moduleId(1).moduleName("analyse1").moduleSemestre(Semestre.SEMESTRE1).build();
        responseModuleDTO2= ResponseModuleDTO.builder().moduleId(2).moduleName("analyse2").moduleSemestre(Semestre.SEMESTRE1).build();
        requestModuleDTO2=RequestModuleDTO.builder().moduleId(2).moduleName("analyse2").moduleSemestre(Semestre.SEMESTRE1).build();
        responseModuleDTOS= new ArrayList<>();
        responseModuleDTOS.add(responseModuleDTO1);
        responseModuleDTOS.add(responseModuleDTO2);
    }

    @Test
    void souldReturnsListOfModules() {
        when(moduleDAO.findAll()).thenReturn(moduleList);
        when(moduleMapper.modelToDtos(moduleList)).thenReturn(responseModuleDTOS);
        assertResponseModuleDTOSList(moduleService.getModules());
    }
    @Test
    void shouldReturnListOfModulesByName() {
        when(moduleDAO.findModulesByModuleNameLikeIgnoreCase(moduleName)).thenReturn(moduleList);
        when(moduleMapper.modelToDtos(moduleList)).thenReturn(responseModuleDTOS);
        assertResponseModuleDTOSList(moduleService.getModulesByName(moduleName));
    }

    @Test
    void shouldThrowsNoModuleNotFoundExceptionIfListOfModulesIsEmpty() {
        when(moduleDAO.findAll()).thenReturn(Collections.emptyList());

        when(moduleDAO.findModulesByModuleNameLikeIgnoreCase(moduleName)).thenReturn(Collections.emptyList());

        Assertions.assertThatThrownBy(() -> moduleService.getModules())
                .isInstanceOf(ModuleListIsEmptyException.class)
                .hasMessageContaining("The list is empty");

        Assertions.assertThatThrownBy(() -> moduleService.getModulesByName(moduleName))
                .isInstanceOf(ModuleListIsEmptyException.class)
                .hasMessageContaining("The list is empty");

        // Verify that the category repository method was called
        verify(moduleDAO, times(1)).findAll();

        // Verify that the category repository method was called
        verify(moduleDAO, times(1)).findModulesByModuleNameLikeIgnoreCase(moduleName);
    }
    @Test
    void shouldSaveModule() {
        when(moduleDAO.save(Mockito.any(Module.class))).thenReturn(module1);
        when(moduleMapper.modelToDto(module1)).thenReturn(responseModuleDTO1);
        when(moduleMapper.dtoToModule(requestModuleDTO1)).thenReturn(module1);


        ResponseModuleDTO savedModule = moduleService.addModule(requestModuleDTO1);

        Assertions.assertThat(savedModule).isNotNull();
        Assertions.assertThat(savedModule.getModuleName()).isEqualTo(requestModuleDTO1.getModuleName());
        Assertions.assertThat(savedModule.getModuleId()).isEqualTo(requestModuleDTO1.getModuleId());
    }

    @Test
    void shouldReturnModuleById() {
        Optional<Module> moduleOptional=Optional.ofNullable(module1);
        when(moduleDAO.findById(1)).thenReturn(moduleOptional);
        when(moduleMapper.modelToDto(module1)).thenReturn(responseModuleDTO1);

        ResponseModuleDTO gettedModule=moduleService.getModuleById(1);

        Assertions.assertThat(gettedModule).isNotNull();
        Assertions.assertThat(gettedModule.getModuleName()).isEqualTo(moduleOptional.get().getModuleName());
        Assertions.assertThat(gettedModule.getModuleId()).isEqualTo(moduleOptional.get().getModuleId());
    }
    @Test
    void shouldThrowExceptionIfModuleNotFound() {

        when(moduleDAO.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> moduleService.getModuleById(1))
                .isInstanceOf(ModuleNotFoundException.class)
                .hasMessageContaining(String.format("the module with id %d not exist.", 1));

    }
    @Test
    void shouldThrowExceptionIfModuleAlreadyExist() {

        when(moduleDAO.existsByModuleName(module2.getModuleName())).thenReturn(true);

        Assertions.assertThatThrownBy(() -> moduleService.addModule(requestModuleDTO2))
                .isInstanceOf(ModuleAlreadyExistsException.class)
                .hasMessageContaining(String.format("The module with name %s is already exists",module2.getModuleName()));

    }
    @Test
    void shouldUpdateCategory() {

        when(moduleDAO.save(module2)).thenReturn(module2);
        when(moduleMapper.modelToDto(module2)).thenReturn(responseModuleDTO2);
        when(moduleMapper.dtoToModule(requestModuleDTO2)).thenReturn(module2);

        ResponseModuleDTO updateReturn = moduleService.updateModule(requestModuleDTO2);

        Assertions.assertThat(updateReturn).isNotNull();
        Assertions.assertThat(updateReturn.getModuleName()).isEqualTo(module2.getModuleName());
        Assertions.assertThat(updateReturn.getModuleId()).isEqualTo(module2.getModuleId());

    }
    @Test
    void shouldDeleteModuleByName() {

        // Arrange
        String module1Name="analyse1";

        // Act
        moduleService.deleteModuleByName(module1Name);

        // Assert
        verify(moduleDAO, times(1)).deleteByModuleName(module1Name);
    }
    private void assertResponseModuleDTOSList(List<ResponseModuleDTO> moduleDTOS) {

        List<ResponseModuleDTO> moduleDTOS1 = moduleDTOS;

        // Assert that the list is not empty
        Assertions.assertThat(moduleDTOS1).isNotEmpty();
        // Assert that the list contains 2 items
        Assertions.assertThat(moduleDTOS1.size()).isEqualTo(2);
        // Assert that the first item is not null
        Assertions.assertThat(moduleDTOS1.get(0)).isNotEqualTo(null);
        // Assert that the second item is not null
        Assertions.assertThat(moduleDTOS1.get(1)).isNotEqualTo(null);
        // Assert that the name of the first item is the same as the name of the first category
        Assertions.assertThat(moduleDTOS1.get(0).getModuleName()).isEqualTo(module1.getModuleName());
        // Assert that the name of the second item is the same as the name of the second category
        Assertions.assertThat(moduleDTOS1.get(1).getModuleName()).isEqualTo(module2.getModuleName());
    }

    @Test
    void getModulesByName() {
    }

    @Test
    void addModule() {
    }

    @Test
    void updateModule() {
    }

    @Test
    void entityResponseModuleDto() {
    }

    @Test
    void deleteModuleByName() {
    }
}