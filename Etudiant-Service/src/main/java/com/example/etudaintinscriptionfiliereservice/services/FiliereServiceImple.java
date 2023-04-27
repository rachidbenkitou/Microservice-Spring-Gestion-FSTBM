package com.example.etudaintinscriptionfiliereservice.services;

import com.example.etudaintinscriptionfiliereservice.dtos.FiliereRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.FiliereResponseDto;
import com.example.etudaintinscriptionfiliereservice.entities.Filiere;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityAlreadyExistException;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;
import com.example.etudaintinscriptionfiliereservice.exceptions.InvalidEntityException;
import com.example.etudaintinscriptionfiliereservice.mappers.FiliereMapper;
import com.example.etudaintinscriptionfiliereservice.repositories.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of {@link FiliereService} that uses a {@link FiliereRepository} to perform CRUD operations on {@link Filiere} entities.
 */
@Service
@Transactional
public class FiliereServiceImple implements FiliereService {

    private final FiliereRepository filiereRepository;
    private final FiliereMapper filiereMapper;

    @Autowired
    public FiliereServiceImple(FiliereRepository filiereRepository, FiliereMapper filiereMapper) {
        this.filiereRepository = filiereRepository;
        this.filiereMapper = filiereMapper;
    }

    /**
     * Returns all {@link Filiere} entities in the database.
     *
     * @return a list of all {@link FiliereResponseDto} objects representing the retrieved entities
     */
    @Override
    public List<FiliereResponseDto> getAll() {
        return filiereMapper.fromModels(filiereRepository.findAll());
    }

    /**
     * Returns the {@link Filiere} entity in the database with the given ID.
     *
     * @param idFiliere the ID of the {@link Filiere} entity to retrieve
     * @return a {@link FiliereResponseDto} object representing the retrieved entity
     * @throws EntityNotFoundException if no {@link Filiere} entity with the given ID exists in the database
     */
    @Override
    public FiliereResponseDto getFiliereById(String idFiliere) throws EntityNotFoundException {
        Filiere filiere = filiereRepository.findById(idFiliere)
                .orElseThrow(()->new EntityNotFoundException("No filiere with ID "+idFiliere+" were found"));
        return filiereMapper.fromModel(filiere);
    }

    /**
     * Returns the {@link Filiere} entity in the database with the given name.
     *
     * @param name the name of the {@link Filiere} entity to retrieve
     * @return a {@link FiliereResponseDto} object representing the retrieved entity
     * @throws EntityNotFoundException if no {@link Filiere} entity with the given name exists in the database
     * @throws MethodArgumentNotValidException if the given name is not valid
     */
    @Override
    public FiliereResponseDto getFiliereByNmae(String name) throws EntityNotFoundException, MethodArgumentNotValidException {
        Optional<Filiere> filiere = Optional.ofNullable(filiereRepository.findByName(name));
        if(!filiere.isPresent()) throw new EntityNotFoundException("No filiere with name "+name+" were found");
        return filiereMapper.fromModel(filiere.get());
    }
    /**
     * Saves a new {@link Filiere} entity in the database.
     *
     * @param filiereRequestDto the {@link FiliereRequestDto} object representing the entity to be saved
     * @return a {@link FiliereResponseDto} object representing the saved entity
     * @throws EntityAlreadyExistException if a {@link Filiere} entity with the same name already exists in the database
     * @throws InvalidEntityException if the {@link Filiere} entity to be saved is not valid
     */
    @Override
    public FiliereResponseDto save(FiliereRequestDto filiereRequestDto) throws EntityAlreadyExistException, InvalidEntityException {
        if (filiereRequestDto.equals(null))throw new InvalidEntityException("Filiere Not Valid");
        Optional<Filiere> filiere = Optional.ofNullable(filiereRepository.findByName(filiereRequestDto.getName()));
        if(filiere.isPresent()) throw new EntityAlreadyExistException("Filiere with name "+filiereRequestDto.getName()+" already exists");
        filiereRequestDto.setIdFiliere(UUID.randomUUID().toString());
        return filiereMapper.fromModel(filiereRepository.save(filiereMapper.toModel(filiereRequestDto)));
    }

    /**
     * Updates an existing {@link Filiere} entity in the database.
     *
     * @param filiereRequestDto the {@link FiliereRequestDto} object representing the entity to be updated
     * @return a {@link FiliereResponseDto} object representing the updated entity
     * @throws InvalidEntityException if the {@link Filiere} entity to be updated is not valid
     */
    @Override
    public FiliereResponseDto update(FiliereRequestDto filiereRequestDto) throws InvalidEntityException {
        if (filiereRequestDto.equals(null))throw new InvalidEntityException("Filiere Not Valid");
        return filiereMapper.fromModel(filiereRepository.save(filiereMapper.toModel(filiereRequestDto)));
    }

    /**
     * Deletes the {@link Filiere} entity in the database with the given ID.
     *
     * @param id the ID of the {@link Filiere} entity to be deleted
     * @throws EntityNotFoundException if no {@link Filiere} entity with the given ID exists in the database
     */
    @Override
    public void delete(String id) throws EntityNotFoundException {
        if(!filiereRepository.findById(id).isPresent()) throw new EntityNotFoundException("No filiere with ID "+id+" were found");
        filiereRepository.deleteById(id);
    }
}
