package com.example.note2service.Services.Impl;

import com.example.note2service.DAO.ExamenDAO;
import com.example.note2service.DAO.NoteDAO;
import com.example.note2service.DTO.RequesteNoteDTO;
import com.example.note2service.DTO.ResponseModuleDTO;
import com.example.note2service.DTO.ResponseNoteDTO;
import com.example.note2service.Entities.*;
import com.example.note2service.Entities.Module;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import com.example.note2service.Exceptions.NoteNotFoundException;
import com.example.note2service.Exceptions.NoteOrdinaireNotExistException;
import com.example.note2service.Mappers.NoteMapper;
import com.example.note2service.Openfeign.EtudiantRestClient;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {
    @Mock
    private NoteDAO noteDAO;
    @Mock
    private NoteMapper noteMapper;

    @Mock
    private ExamenDAO examenDAO;
    @Mock
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @Mock
    private CircuitBreaker circuitBreaker;
    @InjectMocks
    private NoteServiceImpl noteService;
    @Mock
    private EtudiantRestClient etudiantRestClient;
    Examen examen;
    Module module;
    @BeforeEach
    public void init() {
        module = Module.builder().moduleId(1).moduleName("math").moduleSemestre(Semestre.SEMESTRE1).build();
        examen = Examen.builder().id(1).dateExam(new Date()).creationDateTime(new Date())
                .type(TypeExamen.ORDINAIRE).module(module).build();

    }
    @Test
    void getAllNotes() {
        //Arrange
        List<Note> noteList = new ArrayList<>();
            noteList.add(Note.builder().id(new NoteKey("aaa", 1)).mention("Bien")
                    .note(13).oldNote(0).etudiantApogee(10200).build());
        noteList.add(Note.builder().id(new NoteKey("BBB", 1)).mention("Tres Bien")
                .note(16).oldNote(0).etudiantApogee(10201).build());
        List<Etudiant> etudiantList = new ArrayList<>();
        etudiantList.add( Etudiant.builder().id("aaa").nom("youssef").prenom("mahdoubi").adress("dsds").ville("khouribga").apogee(10200L).tel(2232424L).cin("Q1334").dateNaissance(new Date()).email("YSF@gmail.com").build());
        etudiantList.add(Etudiant.builder().id("BBB").nom("rachid").prenom("benkitou").adress("dsds").ville("casablanca").apogee(10201L).tel(2232424L).cin("Q13345").dateNaissance(new Date()).email("RACHID@gmail.com").build());
        when(noteDAO.findAll()).thenReturn(noteList);
        when(etudiantRestClient.findEtudiantById("aaa")).thenReturn(etudiantList.get(0));
        when(etudiantRestClient.findEtudiantById("BBB")).thenReturn(etudiantList.get(1));
        List<ResponseNoteDTO> responseNoteDTOS = new ArrayList<>();
        responseNoteDTOS.add(ResponseNoteDTO.builder().id(new NoteKey("aaa", 1)).mention("Bien")
                .note(13).oldNote(0).etudiant(etudiantList.get(0)).examen(examen).build());
        responseNoteDTOS.add(ResponseNoteDTO.builder().id(new NoteKey("BBB", 1)).mention("Tres Bien")
                .note(16).oldNote(0).etudiant(etudiantList.get(1)).examen(examen).build());
        when(noteMapper.modelToDtos(noteList)).thenReturn(responseNoteDTOS);
        // When
        List<ResponseNoteDTO>  actualResponseNoteDTOs  = noteService.getAllNotes();

        // Then
        assertEquals(2,  actualResponseNoteDTOs .size());
        assertEquals(etudiantList.get(0), actualResponseNoteDTOs .get(0).getEtudiant());
        assertEquals(etudiantList.get(1), actualResponseNoteDTOs .get(1).getEtudiant());
        assertEquals(responseNoteDTOS, actualResponseNoteDTOs );
        // Verify that the DAO and RestClient methods were called
        verify(noteDAO, times(1)).findAll();
        verify(etudiantRestClient, times(2)).findEtudiantById(anyString());
        verify(noteMapper, times(1)).modelToDtos(noteList);


    }

    @Test
    void testFallbackGetAllNotes() {
        //Arrange

        List<Note> noteList = new ArrayList<>();
        noteList.add(Note.builder().id(new NoteKey("aaa", 1)).mention("Bien")
                .note(13).oldNote(0).etudiantApogee(10200).build());
        noteList.add(Note.builder().id(new NoteKey("BBB", 1)).mention("Tres Bien")
                .note(16).oldNote(0).etudiantApogee(10201).build());
        when(noteDAO.findAll()).thenReturn(noteList);
        Exception exception = new Exception();
        List<ResponseNoteDTO> responseNoteDTOS = new ArrayList<>();
        responseNoteDTOS.add(ResponseNoteDTO.builder().id(new NoteKey("aaa", 1)).mention("Bien")
                .note(13).oldNote(0).etudiant(null).examen(examen).build());
        responseNoteDTOS.add(ResponseNoteDTO.builder().id(new NoteKey("BBB", 1)).mention("Tres Bien")
                .note(16).oldNote(0).etudiant(null).examen(examen).build());
        when(noteMapper.modelToDtos(noteList)).thenReturn(responseNoteDTOS);

        // Call the method under test
        List<ResponseNoteDTO> result = noteService.fallbackGetAllNotes(exception);
        // Verify the results
        assertEquals(result,responseNoteDTOS);
        // Verify that the DAO method was called
        verify(noteDAO, times(1)).findAll();
        verify(noteMapper, times(1)).modelToDtos(noteList);
    }


    @Test
    void getNoteById() throws NoteNotFoundException {
        //Arrange
         NoteKey id=new NoteKey("aaa", 1);
          Note note = Note.builder().id(id).mention("Bien")
                  .note(13).oldNote(0).etudiantApogee(10200).build();
        when(noteDAO.findById(id)).thenReturn(Optional.of(note));
        Etudiant etudiant = Etudiant.builder().id("aaa").nom("youssef").prenom("mahdoubi").adress("dsds").ville("khouribga").apogee(10200L).tel(2232424L).cin("Q1334").dateNaissance(new Date()).email("YSF@gmail.com").build();
        when(etudiantRestClient.findEtudiantById("aaa")).thenReturn(etudiant);
        ResponseNoteDTO responseNoteDTO = ResponseNoteDTO.builder().id(new NoteKey("aaa", 1)).mention("Bien")
                .note(13).oldNote(0).etudiant(etudiant).examen(examen).build();
        when(noteMapper.modelToDto(note)).thenReturn(responseNoteDTO);
        //Act
        ResponseNoteDTO result = noteService.getNoteById(id);
        //Assert
        //Assert
        org.assertj.core.api.Assertions.assertThat(result).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(responseNoteDTO);
    }

    @Test
    void fallbackGetNoteById() throws NoteNotFoundException {
        //Arrange
        NoteKey id=new NoteKey("aaa", 1);
        Note note = Note.builder().id(id).mention("Bien")
                .note(13).oldNote(0).etudiantApogee(10200).build();
        when(noteDAO.findById(id)).thenReturn(Optional.of(note));
        Exception exception = new Exception();
        ResponseNoteDTO responseNoteDTO = ResponseNoteDTO.builder().id(new NoteKey("aaa", 1)).mention("Bien")
                .note(13).oldNote(0).etudiant(null).examen(examen).build();
        when(noteMapper.modelToDto(note)).thenReturn(responseNoteDTO);
        // Call the method under test
             ResponseNoteDTO result = noteService.fallbackGetNoteById(id ,exception);
        // Verify the results
        assertEquals(result,responseNoteDTO);
        // Verify that the DAO method was called
        verify(noteDAO, times(1)).findById(id);
        verify(noteMapper, times(1)).modelToDto(note);
    }
    @Test
    void getExamenById_shouldThrowExamenNotFoundException_whenExamenNotFound() {
        //Arrange
        NoteKey id=new NoteKey("aaa", 1);
        when(noteDAO.findById(id)).thenReturn(Optional.empty());
        //Act
        NoteNotFoundException exception = Assertions.assertThrows( NoteNotFoundException.class
                ,()-> noteService.getNoteById(id));
        //Assert
        org.assertj.core.api.Assertions.assertThat(exception).isNotNull();
        org.assertj.core.api.Assertions.assertThat("Il n' y a aucune note avec ce ID")
                .isEqualTo(exception.getMessage());
    }

    @Test
    void deleteNote() {
        NoteKey id=new NoteKey("aaa", 1);
        noteService.deleteNote(id);
        verify(noteDAO).deleteById(id);
    }
}