package com.example.note2service.Services.Impl;

import com.example.note2service.DAO.ExamenDAO;
import com.example.note2service.DTO.RequestExamenDTO;
import com.example.note2service.DTO.ResponseExamenDTO;
import com.example.note2service.DTO.ResponseModuleDTO;
import com.example.note2service.Entities.Examen;
import com.example.note2service.Entities.Module;
import com.example.note2service.Entities.Semestre;
import com.example.note2service.Entities.TypeExamen;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import com.example.note2service.Mappers.ExamenMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamenServiceImplTest {

    @Mock
    private ExamenDAO examenDAO;
    @Mock
    private ExamenMapper examenMapper;
    @InjectMocks
    private ExamenServiceImpl examenService;
    Module module;
    ResponseModuleDTO moduleDTO;

    @BeforeEach
    public void init() {
        module = Module.builder().moduleId(1).moduleName("math").moduleSemestre(Semestre.SEMESTRE1).build();
        moduleDTO = ResponseModuleDTO.builder().moduleId(1).moduleName("math").moduleSemestre(Semestre.SEMESTRE1).build();
    }
    @Test
    void getAllExamens() {
        //Arrange
        List<Examen> examenList = new ArrayList<>();
        examenList.add(Examen.builder().id(1).dateExam(new Date()).creationDateTime(new Date())
                .type(TypeExamen.ORDINAIRE).module(module).build());
        examenList.add(Examen.builder().id(2).dateExam(new Date()).creationDateTime(new Date())
                .type(TypeExamen.RATTRAPAGE).module(module).build());
        List<ResponseExamenDTO>examenDTOList = new ArrayList<>();
         examenDTOList.add(ResponseExamenDTO.builder().id(1).dateExam(new Date()).creationDateTime(new Date())
                 .type(TypeExamen.ORDINAIRE).module(module).build());
        examenDTOList.add(ResponseExamenDTO.builder().id(2).dateExam(new Date()).creationDateTime(new Date())
                .type(TypeExamen.RATTRAPAGE).module(module).build());
        when(examenDAO.findAll()).thenReturn(examenList);
        when(examenMapper.modelToDtos(examenList)).thenReturn(examenDTOList);
        //Act
        List<ResponseExamenDTO>result = examenService.getAllExamens();
        //Assert
        org.assertj.core.api.Assertions.assertThat(result).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(examenDTOList);
    }

    @Test
    void getExamenById() {
        //Arrange
        long id =1;
        Examen examen = Examen.builder().id(1).dateExam(new Date()).creationDateTime(new Date())
                .type(TypeExamen.ORDINAIRE).module(module).build();
        ResponseExamenDTO responseExamenDTO = ResponseExamenDTO.builder().id(1).dateExam(new Date()).creationDateTime(new Date())
                .type(TypeExamen.ORDINAIRE).module(module).build();
        when(examenDAO.findById(id)).thenReturn(Optional.of(examen));
        when(examenMapper.modelToDto(examen)).thenReturn(responseExamenDTO);
        //Act
        ResponseExamenDTO result = examenService.getExamenById(id);
        //Assert
        org.assertj.core.api.Assertions.assertThat(result).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(responseExamenDTO);
    }
    @Test
    void getExamenById_shouldThrowExamenNotFoundException_whenExamenNotFound() {
        //Arrange
        long id = 1;
        when(examenDAO.findById(id)).thenReturn(Optional.empty());
        //Act
        ExamenNotFoundException exception = Assertions.assertThrows(ExamenNotFoundException.class
                ,()-> examenService.getExamenById(id));
        //Assert
        org.assertj.core.api.Assertions.assertThat(exception).isNotNull();
        org.assertj.core.api.Assertions.assertThat("Il n' y a aucun examen avec cet ID")
                .isEqualTo(exception.getMessage());
    }
    @Test
    void saveExamen(){
        //Arrange
        Examen examen =Examen.builder().id(1).dateExam(new Date()).creationDateTime(new Date())
                .type(TypeExamen.ORDINAIRE).module(module).build();
        RequestExamenDTO requestExamenDTO = RequestExamenDTO.builder().id(1).dateExam(new Date()).creationDateTime(new Date())
                .type(TypeExamen.ORDINAIRE).module(module).build();
        ResponseExamenDTO responseExamenDTO = ResponseExamenDTO.builder().id(1).dateExam(new Date()).creationDateTime(new Date())
                .type(TypeExamen.ORDINAIRE).module(module).build();
        when(examenMapper.dtoToModel(requestExamenDTO)).thenReturn(examen);
        when(examenDAO.save(Mockito.any(Examen.class))).thenReturn(examen);
        when(examenMapper.modelToDto(examen)).thenReturn(responseExamenDTO);
        //Act
        ResponseExamenDTO savedExamenDTO = examenService.addExamen(requestExamenDTO);
        //Assert
        org.assertj.core.api.Assertions.assertThat(savedExamenDTO).isNotNull();
        org.assertj.core.api.Assertions.assertThat(savedExamenDTO).isEqualTo(responseExamenDTO);
    }
    @Test
    void addExamen() {
        RequestExamenDTO requestExamenDTO = RequestExamenDTO.builder().id(1).dateExam(new Date()).creationDateTime(new Date())
                .type(TypeExamen.ORDINAIRE).module(module).build();
        ResponseExamenDTO  savedExamen=examenService.addExamen(requestExamenDTO);
        ResponseExamenDTO responseExamenDTO =examenService.saveExamen(requestExamenDTO);
        org.assertj.core.api.Assertions.assertThat( savedExamen).isEqualTo(responseExamenDTO);
    }

    @Test
    void updateExamen() {
        RequestExamenDTO requestExamenDTO = RequestExamenDTO.builder().id(1).dateExam(new Date()).creationDateTime(new Date())
                .type(TypeExamen.ORDINAIRE).module(module).build();
        ResponseExamenDTO  updatedExamen=examenService.UpdateExamen(requestExamenDTO);
        ResponseExamenDTO responseExamenDTO =examenService.saveExamen(requestExamenDTO);
        org.assertj.core.api.Assertions.assertThat(updatedExamen).isEqualTo(responseExamenDTO);

    }

    @Test
    void deleteExamen() {
        long examenId = 1;
        examenService.deleteExamen(examenId);
        verify(examenDAO).deleteById(examenId);
    }
}