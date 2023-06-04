package com.example.etudaintinscriptionfiliereservice.services;


import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDto;
import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDto;
import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityAlreadyExistException;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;
import com.example.etudaintinscriptionfiliereservice.exceptions.InvalidEntityException;
import com.example.etudaintinscriptionfiliereservice.mappers.EtudiantMapper;
import com.example.etudaintinscriptionfiliereservice.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
public class EtudiantServiceImpl implements EtudiantService {
    private final EtudiantRepository etudiantRepository;
    private final EtudiantMapper etudiantMapper;

    @Autowired
    public EtudiantServiceImpl(EtudiantRepository etudiantRepository, EtudiantMapper etudiantMapper) {
        this.etudiantRepository = etudiantRepository;
        this.etudiantMapper = etudiantMapper;
    }
    @Override
    public  List<ResponseEtudiantDto> getAllEtudiants(){
        return etudiantMapper.fromModels(etudiantRepository.findAll());
    }

    @Override
    public ResponseEtudiantDto getEtudiantById(String idEtudiant) throws EntityNotFoundException {
        Etudiant etudiant=etudiantRepository.findById(idEtudiant).orElseThrow(()->new EntityNotFoundException("L' ID: "+idEtudiant+"n'existe pas"));
        return etudiantMapper.fromModel(etudiant);
   }

    @Override
    public ResponseEtudiantDto getEtudiantByApogee(Long apogee) throws EntityNotFoundException{
        Optional<Etudiant> etudiant = Optional.ofNullable(etudiantRepository.findByApogee(apogee));
        if(!etudiant.isPresent()) throw new EntityNotFoundException("L' APOGEE: "+apogee+"n'existe pas");
        return etudiantMapper.fromModel(etudiant.get());
    }

    @Override
    public ResponseEtudiantDto getEtudiantByCin(String cin) {
        Optional<Etudiant> etudiant = Optional.ofNullable(
                etudiantRepository.findByCin(cin)
                        .orElseThrow(() ->
                                new EntityNotFoundException("L' cin: " + cin + "n'existe pas")));
        return etudiantMapper.fromModel(etudiant.get());
    }

    @Override
    public ResponseEtudiantDto addEtudiant(RequestEtudiantDto requestEtudiantDTo) throws EntityAlreadyExistException , InvalidEntityException {
        if(requestEtudiantDTo.equals(null))
            throw new InvalidEntityException("Etudiant Not Valid");
       Optional<Etudiant> etudiant = etudiantRepository.findByCin(requestEtudiantDTo.getCin());
        if(etudiant.isPresent()) throw new EntityAlreadyExistException(" L' CIN: "+requestEtudiantDTo.getCin()+" existe deja");
        requestEtudiantDTo.setId(UUID.randomUUID().toString());
        Long uniqueApogee = Long.valueOf(System.currentTimeMillis());
        requestEtudiantDTo.setApogee(uniqueApogee);
        return etudiantMapper.fromModel(etudiantRepository.save(etudiantMapper.toModel(requestEtudiantDTo)));
    }

    @Override
    public ResponseEtudiantDto updateEtudiant(RequestEtudiantDto requestEtudiantDTo) throws InvalidEntityException {
        if(requestEtudiantDTo.equals(null)) throw new InvalidEntityException("Etudiant Not Valid");
        ResponseEtudiantDto saved =etudiantMapper.fromModel(etudiantRepository.save(etudiantMapper.toModel(requestEtudiantDTo)));
        if(saved!= null)  return saved;
        else throw new EntityNotFoundException("etudiant not found");
    }

    @Override
    public void deleteEtudiant(String etudiantId) throws EntityNotFoundException {
        if(!etudiantRepository.findById(etudiantId).isPresent()) throw new EntityNotFoundException("l' etudiant du ID "+etudiantId+" n'existe pas");
        etudiantRepository.deleteById(etudiantId);
    }

    }












