package com.example.etudaintinscriptionfiliereservice.services;

import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionResponseDto;
import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDto;
import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDto;
import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import com.example.etudaintinscriptionfiliereservice.entities.Inscription;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityAlreadyExistException;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;
import com.example.etudaintinscriptionfiliereservice.exceptions.InvalidEntityException;
import com.example.etudaintinscriptionfiliereservice.mappers.EtudiantMapper;
import com.example.etudaintinscriptionfiliereservice.mappers.InscriptionMapper;
import com.example.etudaintinscriptionfiliereservice.repositories.InscriptionRepository;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class InscriptionServiceImplTest {
//    @InjectMocks
//    private InscriptionServiceImpl inscriptionService;
//    @Mock
//    private InscriptionRepository inscriptionRepository;
//    @Mock
//    private InscriptionMapper inscriptionMapper;
//
//    @Mock
//    private EtudiantMapper etudiantMapper;
//    private List<Inscription> myInscriptions;
//    private List<InscriptionResponseDto> myResponseInscriptionDto;
//    private Inscription myInscription;
//    private InscriptionResponseDto myResponseInscription;
//    private InscriptionRequestDto myRequestInscriptionDto;
//
//    @BeforeEach
//    void setUp() {
//        myInscriptions = new ArrayList<>();
//        myInscriptions.add(new Inscription("id1", new Date(), null, null));
//        myInscriptions.add(new Inscription("id2", new Date(), null, null));
//        myInscriptions.add(new Inscription("id3", new Date(), null, null));
//
//        myResponseInscriptionDto = new ArrayList<>();
//        myResponseInscriptionDto.add(new InscriptionResponseDto("id1", new Date(), null, null));
//        myResponseInscriptionDto.add(new InscriptionResponseDto("id2", new Date(), null, null));
//        myResponseInscriptionDto.add(new InscriptionResponseDto("id3", new Date(), null, null));
//
//        myInscription = new Inscription("id1", new Date(), null, null);
//        myResponseInscription = new InscriptionResponseDto("id1", new Date(), null, null);
//        myRequestInscriptionDto = new InscriptionRequestDto("id1", new Date(), null, null);
//    }
//
//    @Test
//    void testGetAll() {
//        // arrange
//        when(inscriptionRepository.findAll()).thenReturn(myInscriptions);
//        when(inscriptionMapper.fromModels(myInscriptions)).thenReturn(myResponseInscriptionDto);
//
//        // act
//        List<InscriptionResponseDto> result = inscriptionService.getAll();
//
//        // assert
//        assertEquals(myResponseInscriptionDto, result);
//        verify(inscriptionRepository).findAll();
//        verify(inscriptionMapper).fromModels(myInscriptions);
//    }
//
//    @Test
//    void testGetInscription() throws EntityNotFoundException {
//        // Arrange
//        String id = "id1";
//        when(inscriptionRepository.findById(id))
//                .thenReturn(Optional.of(myInscription));
//        when(inscriptionMapper.fromModel(myInscription))
//                .thenReturn(myResponseInscription);
//
//        // Act
//        InscriptionResponseDto result = inscriptionService.getInscription(id);
//
//        // Assert
//        assertEquals(myResponseInscription, result);
//        verify(inscriptionRepository).findById(id);
//        verify(inscriptionMapper).fromModel(myInscription);
//    }
//
//    @Test
//    void testGetInscriptionThrowsEntityNotFoundException() {
//        // Arrange
//        String id = "id1";
//        when(inscriptionRepository.findById(id)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(EntityNotFoundException.class, () -> {
//            inscriptionService.getInscription(id);
//        });
//        verify(inscriptionRepository).findById(id);
//    }
//    @Test
//    void save_validInscriptionRequestDto_returnsSavedInscriptionResponseDto() throws EntityAlreadyExistException, InvalidEntityException {
//        // Arrange
//        RequestEtudiantDto requestEtudiantDto = new RequestEtudiantDto();
//        requestEtudiantDto.setId("1");
//        requestEtudiantDto.setApogee(1234567890L);
//        requestEtudiantDto.setNom("Doe");
//        requestEtudiantDto.setPrenom("John");
//        requestEtudiantDto.setEmail("johndoe@example.com");
//        requestEtudiantDto.setDateNaissance(new Date());
//        requestEtudiantDto.setTel("1234567890L");
//        requestEtudiantDto.setVille("New York");
//        requestEtudiantDto.setAdress("123 Main St");
//
//        ResponseEtudiantDto responseEtudiantDto = new ResponseEtudiantDto();
//        responseEtudiantDto.setId("1");
//        responseEtudiantDto.setApogee(1234567890L);
//        responseEtudiantDto.setNom("Doe");
//        responseEtudiantDto.setPrenom("John");
//        responseEtudiantDto.setEmail("johndoe@example.com");
//        responseEtudiantDto.setDateNaissance(new Date());
//        responseEtudiantDto.setTel("1234567890L");
//        responseEtudiantDto.setVille("New York");
//        responseEtudiantDto.setAdress("123 Main St");
//
//        Etudiant etudiant = new Etudiant();
//        etudiant.setId("1");
//        etudiant.setApogee(1234567890L);
//        etudiant.setNom("Doe");
//        etudiant.setPrenom("John");
//        etudiant.setEmail("johndoe@example.com");
//        etudiant.setDateNaissance(new Date());
//        etudiant.setTel("1234567890L");
//        etudiant.setVille("New York");
//        etudiant.setAdress("123 Main St");
//
//        InscriptionRequestDto validInscriptionRequestDto = new InscriptionRequestDto("id4", new Date(), null, requestEtudiantDto);
//        Inscription savedInscription = new Inscription("id4", new Date(), null, etudiant);
//        InscriptionResponseDto expectedResponseDto = new InscriptionResponseDto("id4", new Date(), null, responseEtudiantDto);
//
//        when(inscriptionRepository.existsByEtudiant(etudiantMapper.toModel(validInscriptionRequestDto.getRequestEtudiantDto()))).thenReturn(false);
//        when(inscriptionMapper.toModel(validInscriptionRequestDto)).thenReturn(savedInscription);
//        when(inscriptionRepository.save(savedInscription)).thenReturn(savedInscription);
//        when(inscriptionMapper.fromModel(savedInscription)).thenReturn(expectedResponseDto);
//
//        // Act
//        InscriptionResponseDto actualResponseDto = inscriptionService.save(validInscriptionRequestDto);
//
//        // Assert
//        assertEquals(expectedResponseDto, actualResponseDto);
//        verify(inscriptionRepository).existsByEtudiant(etudiantMapper.toModel(validInscriptionRequestDto.getRequestEtudiantDto()));
//        verify(inscriptionMapper).toModel(validInscriptionRequestDto);
//        verify(inscriptionRepository).save(savedInscription);
//        verify(inscriptionMapper).fromModel(savedInscription);
//    }
//    @Test
//    void update_validInscriptionRequestDto_returnsUpdatedInscriptionResponseDto() throws InvalidEntityException {
//        // Arrange
//        RequestEtudiantDto requestEtudiantDto = new RequestEtudiantDto();
//        requestEtudiantDto.setId("1");
//        requestEtudiantDto.setApogee(1234567890L);
//        requestEtudiantDto.setNom("Doe");
//        requestEtudiantDto.setPrenom("John");
//        requestEtudiantDto.setEmail("johndoe@example.com");
//        requestEtudiantDto.setDateNaissance(new Date());
//        requestEtudiantDto.setTel("1234567890L");
//        requestEtudiantDto.setVille("New York");
//        requestEtudiantDto.setAdress("123 Main St");
//
//        ResponseEtudiantDto responseEtudiantDto = new ResponseEtudiantDto();
//        responseEtudiantDto.setId("1");
//        responseEtudiantDto.setApogee(1234567890L);
//        responseEtudiantDto.setNom("Doe");
//        responseEtudiantDto.setPrenom("John");
//        responseEtudiantDto.setEmail("johndoe@example.com");
//        responseEtudiantDto.setDateNaissance(new Date());
//        responseEtudiantDto.setTel("1234567890L");
//        responseEtudiantDto.setVille("New York");
//        responseEtudiantDto.setAdress("123 Main St");
//
//        Etudiant etudiant = new Etudiant();
//        etudiant.setId("1");
//        etudiant.setApogee(1234567890L);
//        etudiant.setNom("Doe");
//        etudiant.setPrenom("John");
//        etudiant.setEmail("johndoe@example.com");
//        etudiant.setDateNaissance(new Date());
//        etudiant.setTel("1234567890L");
//        etudiant.setVille("New York");
//        etudiant.setAdress("123 Main St");
//
//        InscriptionRequestDto validInscriptionRequestDto = new InscriptionRequestDto("id4", new Date(), null, requestEtudiantDto);
//        Inscription updatedInscription = new Inscription("id4", new Date(),null, etudiant);
//        InscriptionResponseDto expectedResponseDto = new InscriptionResponseDto("id4", new Date(), null, responseEtudiantDto);
//
//        when(inscriptionMapper.toModel(validInscriptionRequestDto)).thenReturn(updatedInscription);
//        when(inscriptionRepository.save(updatedInscription)).thenReturn(updatedInscription);
//        when(inscriptionMapper.fromModel(updatedInscription)).thenReturn(expectedResponseDto);
//
//        // Act
//        InscriptionResponseDto actualResponseDto = inscriptionService.update(validInscriptionRequestDto);
//
//        // Assert
//        assertEquals(expectedResponseDto, actualResponseDto);
//        verify(inscriptionMapper).toModel(validInscriptionRequestDto);
//        verify(inscriptionRepository).save(updatedInscription);
//        verify(inscriptionMapper).fromModel(updatedInscription);
//    }
//    @Test
//    void delete_existingId_deletesEntity() throws EntityNotFoundException {
//        // Arrange
//        String existingId = "existing-id";
//
//        when(inscriptionRepository.findById(existingId)).thenReturn(Optional.of(new Inscription()));
//
//        // Act
//        inscriptionService.delete(existingId);
//
//        // Assert
//        verify(inscriptionRepository).findById(existingId);
//        verify(inscriptionRepository).deleteById(existingId);
//    }
//
//    @Test
//    void delete_nonExistingId_throwsEntityNotFoundException() {
//        // Arrange
//        String nonExistingId = "non-existing-id";
//
//        when(inscriptionRepository.findById(nonExistingId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(EntityNotFoundException.class, () -> inscriptionService.delete(nonExistingId));
//
//        verify(inscriptionRepository).findById(nonExistingId);
//        verify(inscriptionRepository, never()).deleteById(anyString());
//    }


}