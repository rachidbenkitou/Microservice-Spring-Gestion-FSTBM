package com.example.etudaintinscriptionfiliereservice.services;

import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDto;
import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDto;
import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;
import com.example.etudaintinscriptionfiliereservice.mappers.EtudiantMapper;
import com.example.etudaintinscriptionfiliereservice.repositories.EtudiantRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private EtudiantMapper etudiantMapper;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private RequestEtudiantDto requestEtudiantDto;
    private ResponseEtudiantDto responseEtudiantDto;
    private Etudiant etudiant;
    private String etudiantId;

    @BeforeEach
    void setUp() {
        requestEtudiantDto = new RequestEtudiantDto();
        etudiantId=UUID.randomUUID().toString();
        requestEtudiantDto.setId(etudiantId);
        requestEtudiantDto.setNom("Doe");
        requestEtudiantDto.setPrenom("John");
        requestEtudiantDto.setApogee(123456789L);

        etudiant = new Etudiant();
        etudiant.setId(etudiantId);
        etudiant.setNom("Doe");
        etudiant.setPrenom("John");
        etudiant.setApogee(123456789L);

        responseEtudiantDto = new ResponseEtudiantDto();
        responseEtudiantDto.setId(etudiant.getId());
        responseEtudiantDto.setNom(etudiant.getNom());
        responseEtudiantDto.setPrenom(etudiant.getPrenom());
        responseEtudiantDto.setApogee(etudiant.getApogee());
    }

    @Test
    void getAllEtudiants() {
       List etudiantList = Arrays.asList(etudiant);
        when(etudiantRepository.findAll()).thenReturn(etudiantList);
        when(etudiantMapper.fromModels(etudiantList)).thenReturn(Arrays.asList(responseEtudiantDto));

        List<ResponseEtudiantDto> etudiants = etudiantService.getAllEtudiants();

        assertNotNull(etudiants);
        assertEquals(1, etudiants.size());
    }

    @Test
    void getEtudiantById() throws EntityNotFoundException {
        when(etudiantRepository.findById(anyString())).thenReturn(Optional.of(new Etudiant()));
        when(etudiantMapper.fromModel(any(Etudiant.class))).thenReturn(responseEtudiantDto);

        ResponseEtudiantDto etudiant = etudiantService.getEtudiantById(UUID.randomUUID().toString());

        assertNotNull(etudiant);
    }

    @Test
    void getEtudiantById_EntityNotFoundException() {
        when(etudiantRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> etudiantService.getEtudiantById(UUID.randomUUID().toString()));
    }
    @Test
    void getEtudiantByApogee() throws EntityNotFoundException {
        when(etudiantRepository.findByApogee(anyLong())).thenReturn(new Etudiant());
        when(etudiantMapper.fromModel(any(Etudiant.class))).thenReturn(responseEtudiantDto);

        ResponseEtudiantDto etudiant = etudiantService.getEtudiantByApogee(requestEtudiantDto.getApogee());

        assertNotNull(etudiant);
    }

    @Test
    void getEtudiantByApogee_EntityNotFoundException() {
        when(etudiantRepository.findByApogee(anyLong())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> etudiantService.getEtudiantByApogee(requestEtudiantDto.getApogee()));
    }

    @Test
    void createEtudiant() {
        when(etudiantMapper.toModel(requestEtudiantDto)).thenReturn(new Etudiant());
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(new Etudiant());
        when(etudiantMapper.fromModel(any(Etudiant.class))).thenReturn(responseEtudiantDto);

        ResponseEtudiantDto etudiant = etudiantService.addEtudiant(requestEtudiantDto);

        assertNotNull(etudiant);
    }
    @Test
    void updateEtudiant() throws EntityNotFoundException {
        when(etudiantMapper.toModel(requestEtudiantDto)).thenReturn(etudiant);
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        when(etudiantMapper.fromModel(etudiant)).thenReturn(responseEtudiantDto);

        ResponseEtudiantDto updatedEtudiant = etudiantService.updateEtudiant(requestEtudiantDto);

        assertNotNull(updatedEtudiant);
    }

    @Test
    void updateEtudiant_EntityNotFoundException() {
        when(etudiantMapper.toModel(requestEtudiantDto)).thenReturn(etudiant);
        when(etudiantRepository.save(etudiant)).thenReturn(null);
        when(etudiantMapper.fromModel(null)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> etudiantService.updateEtudiant(requestEtudiantDto));
    }

    @Test
    void deleteEtudiant() throws EntityNotFoundException {
        when(etudiantRepository.findById(anyString())).thenReturn(Optional.of(etudiant));
        doNothing().when(etudiantRepository).deleteById(anyString());

        etudiantService.deleteEtudiant(etudiantId);

        verify(etudiantRepository, times(1)).deleteById(etudiantId);
    }

    @Test
    void deleteEtudiant_EntityNotFoundException() {
        when(etudiantRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> etudiantService.deleteEtudiant(UUID.randomUUID().toString()));
    }
}

