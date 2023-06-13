package com.example.etudaintinscriptionfiliereservice.services;

import com.example.etudaintinscriptionfiliereservice.dtos.FiliereRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.FiliereResponseDto;
import com.example.etudaintinscriptionfiliereservice.entities.Filiere;
import com.example.etudaintinscriptionfiliereservice.entities.Niveau;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;
import com.example.etudaintinscriptionfiliereservice.mappers.FiliereMapper;
import com.example.etudaintinscriptionfiliereservice.repositories.FiliereRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FiliereServiceImpleTest {
    @Mock
    private FiliereRepository filiereRepository;
    @InjectMocks
    private FiliereServiceImple filiereServiceImple;
    @Mock
    private FiliereMapper filiereMapper;
    private List<Filiere> myFilieres;
    private List<FiliereResponseDto> myResponseFiliereDto;
    private Filiere myFiliere;
    private FiliereResponseDto myResponseFiliere;
    private FiliereRequestDto myRequestFiliereDto;

    @BeforeEach
    void setUp() {
        myFilieres = new ArrayList<>();
        myFilieres.add(new Filiere("id1", "JAVA", Niveau.MST, null));
        myFilieres.add(new Filiere("id2", "Spring", Niveau.LST, null));
        myFilieres.add(new Filiere("id3", "Angular", Niveau.DEUST, null));

        myResponseFiliereDto = new ArrayList<>();
        myResponseFiliereDto.add(new FiliereResponseDto("id1", "JAVA", Niveau.MST, null));
        myResponseFiliereDto.add(new FiliereResponseDto("id2", "Spring", Niveau.LST, null));
        myResponseFiliereDto.add(new FiliereResponseDto("id3", "Angular", Niveau.DEUST, null));

        myFiliere = new Filiere("id1", "JAVA", Niveau.MST, null);
        myResponseFiliere = new FiliereResponseDto("id1", "JAVA", Niveau.MST, null);
        myRequestFiliereDto = new FiliereRequestDto("id1", "JAVA", Niveau.MST, null);
    }

    @Test
    void getAll() {
        when(filiereRepository.findAll()).thenReturn(myFilieres);
        when(filiereMapper.fromModels(myFilieres)).thenReturn(myResponseFiliereDto);

        List<FiliereResponseDto> result = filiereServiceImple.getAll();

        assertEquals(myFilieres.size(), result.size());
    }

    @Test
    void getFiliereById() {
        when(filiereRepository.findById("id1")).thenReturn(Optional.of(myFiliere));
        when(filiereMapper.fromModel(myFiliere)).thenReturn(myResponseFiliere);

        FiliereResponseDto result = filiereServiceImple.getFiliereById("id1");

        assertEquals(myResponseFiliere.getId(), result.getId());
        assertEquals(myResponseFiliere.getName(), result.getName());
        assertEquals(myResponseFiliere.getNiveau(), result.getNiveau());
        assertEquals(myResponseFiliere.getInscriptions(), result.getInscriptions());
    }

    @Test
    void getFiliereByNmae() throws MethodArgumentNotValidException {
        when(filiereRepository.findByName("JAVA")).thenReturn(Optional.of(myFiliere));
        when(filiereMapper.fromModel(myFiliere)).thenReturn(myResponseFiliere);

        FiliereResponseDto result = filiereServiceImple.getFiliereByNmae("JAVA");

        assertEquals(myResponseFiliere.getId(), result.getId());
        assertEquals(myResponseFiliere.getName(), result.getName());
        assertEquals(myResponseFiliere.getNiveau(), result.getNiveau());
        assertEquals(myResponseFiliere.getInscriptions(), result.getInscriptions());
    }

    @Test
    void save() {
        FiliereRequestDto myRequestFiliereDto = new FiliereRequestDto();
        myRequestFiliereDto.setName("JAVA");
        myRequestFiliereDto.setNiveau(Niveau.MST);
        myRequestFiliereDto.setInscriptions(null);
        when(filiereMapper.toModel(myRequestFiliereDto)).thenReturn(myFiliere);
        when(filiereRepository.save(myFiliere)).thenReturn(myFiliere);
        when(filiereMapper.fromModel(myFiliere)).thenReturn(myResponseFiliere);

        FiliereResponseDto result = filiereServiceImple.save(myRequestFiliereDto);
        assertNotNull(result);
        assertEquals(myResponseFiliere, result);
    }
    @Test
    void update() {
        when(filiereMapper.toModel(myRequestFiliereDto)).thenReturn(myFiliere);
        when(filiereRepository.save(myFiliere)).thenReturn(myFiliere);
        when(filiereMapper.fromModel(myFiliere)).thenReturn(myResponseFiliere);

        FiliereResponseDto result = filiereServiceImple.update( myRequestFiliereDto);

        assertNotNull(result);
        assertEquals(myResponseFiliere, result);
    }
    @Test
    void delete_shouldThrowEntityNotFoundException_whenFiliereDoesNotExist() {
        String id = "id1";
        when(filiereRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> filiereServiceImple.delete(id));
        verify(filiereRepository, never()).deleteById(id);
    }

    @Test
    void delete_shouldDeleteFiliere_whenFiliereExists() {
        String id = "id1";
        when(filiereRepository.findById(id)).thenReturn(Optional.of(myFiliere));

        filiereServiceImple.delete(id);
        verify(filiereRepository).deleteById(id);
    }


}