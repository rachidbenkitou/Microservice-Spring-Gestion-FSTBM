package com.example.etudaintinscriptionfiliereservice.services;

import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.InscriptionResponseDto;

import com.example.etudaintinscriptionfiliereservice.entities.Inscription;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityAlreadyExistException;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;
import com.example.etudaintinscriptionfiliereservice.exceptions.InvalidEntityException;
import com.example.etudaintinscriptionfiliereservice.mappers.EtudiantMapper;
import com.example.etudaintinscriptionfiliereservice.mappers.InscriptionMapper;
import com.example.etudaintinscriptionfiliereservice.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * Implementation of {@link InscriptionService} that uses a {@link InscriptionRepository} to perform CRUD operations on {@link Inscription} entities.
 */
@Service
@Transactional
public class InscriptionServiceImpl implements InscriptionService{
    private final InscriptionRepository inscriptionRepository;
    private final InscriptionMapper inscriptionMapper;
    private final EtudiantMapper etudiantMapper;
    @Autowired
    public InscriptionServiceImpl(InscriptionRepository inscriptionRepository, InscriptionMapper inscriptionMapper, EtudiantMapper etudiantMapper) {
        this.inscriptionRepository = inscriptionRepository;
        this.inscriptionMapper = inscriptionMapper;
        this.etudiantMapper = etudiantMapper;
    }
    /**
     * Retrieves a list of all {@link Inscription} entities from the database.
     *
     * @return a list of {@link InscriptionResponseDto} objects representing the retrieved entities
     */
    @Override
    public List<InscriptionResponseDto> getAll() {
        return inscriptionMapper.fromModels(inscriptionRepository.findAll());
    }
    /**
     * Retrieves a {@link Inscription} entity from the database by its ID.
     *
     * @param id the ID of the entity to retrieve
     * @return a {@link InscriptionResponseDto} object representing the retrieved entity
     * @throws EntityNotFoundException if no {@link Inscription} entity with the specified ID is found in the database
     */
    @Override
    public InscriptionResponseDto getInscription(String id) throws EntityNotFoundException {
        return inscriptionMapper.fromModel(
                inscriptionRepository.findById(id)
                        .orElseThrow(()->new EntityNotFoundException("No inscritopn with ID: "+id+" were found")));
    }
    /**
     * Retrieves a {@link Inscription} entity from the database by its ID.
     *
     * @param cin the ID of the entity to retrieve
     * @return a {@link InscriptionResponseDto} object representing the retrieved entity
     * @throws EntityNotFoundException if no {@link Inscription} entity with the specified ID is found in the database
     */
    @Override
    public InscriptionResponseDto getInscriptionByCin(String cin) throws EntityNotFoundException {
        Optional<Inscription> inscription = Optional.ofNullable(
                inscriptionRepository.findByEtudiant_Cin(cin)
                        .orElseThrow(() -> new EntityNotFoundException("No inscritopn with CIN: "+cin+" were found")));
        return inscriptionMapper.fromModel(inscription.get());
    }

    /**
     * Saves a new {@link Inscription} entity in the database.
     *
     * @param inscriptionRequestDto the {@link InscriptionRequestDto} object representing the entity to be saved
     * @return a {@link InscriptionResponseDto} object representing the saved entity
     * @throws EntityAlreadyExistException if a {@link Inscription} entity with the same Etudiant already exists in the database
     * @throws InvalidEntityException if the {@link Inscription} entity to be saved is not valid
     */
    @Override
    public InscriptionResponseDto save(InscriptionRequestDto inscriptionRequestDto) throws EntityAlreadyExistException, InvalidEntityException {
        if(inscriptionRequestDto.equals(null))throw new InvalidEntityException("Inscription not valid");
        if(inscriptionRepository.existsByEtudiant(etudiantMapper.toModel(inscriptionRequestDto.getRequestEtudiantDto())))
            throw new EntityAlreadyExistException("Inscription with Etudiant "+inscriptionRequestDto.getRequestEtudiantDto().getApogee()+" already exists");
        inscriptionRequestDto.setIdInscription(UUID.randomUUID().toString());
        inscriptionRequestDto.setDateInscripton(new Date());
        return inscriptionMapper.fromModel(
                inscriptionRepository.save(
                        inscriptionMapper.toModel(
                                inscriptionRequestDto
                        )
                )
        );
    }
    /**
     * Updates an existing {@link Inscription} entity in the database.
     *
     * @param inscriptionRequestDto the {@link InscriptionRequestDto} object representing the entity to be updated
     * @return a {@link InscriptionResponseDto} object representing the updated entity
     * @throws InvalidEntityException if the {@link Inscription} entity to be updated is not valid
     */
    @Override
    public InscriptionResponseDto update(InscriptionRequestDto inscriptionRequestDto) throws InvalidEntityException {
        if(inscriptionRequestDto.equals(null))throw new InvalidEntityException("Inscription Not Valid");
        return inscriptionMapper.fromModel(inscriptionRepository.save(inscriptionMapper.toModel(inscriptionRequestDto)));
    }
    /**
     * Deletes a {@link Inscription} entity from the database by its ID.
     *
     * @param id the ID of the entity to be deleted
     * @throws EntityNotFoundException if no {@link Inscription} entity with the specified ID is found in the database
     */
    @Override
    public void delete(String id) throws EntityNotFoundException {
        if (!inscriptionRepository.findById(id).isPresent())throw new EntityNotFoundException("No Inscription with ID: "+id+" were found");
        inscriptionRepository.deleteById(id);
    }
}
