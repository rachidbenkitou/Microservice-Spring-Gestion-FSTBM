package com.enseignant.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enseignant.dto.EnseignantDto;
import com.enseignant.entities.Departement;
import com.enseignant.entities.Enseignant;
import com.enseignant.exeption.DepartementNotFoundException;
import com.enseignant.exeption.EnseignantNotFound;
import com.enseignant.mapper.EnseignantMapper;
import com.enseignant.repository.DepartementRepo;
import com.enseignant.repository.EnseignantRepo;
import com.enseignant.service.EnseignantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public  class EnseignantServiceImp implements EnseignantService {
	
	private final EnseignantRepo enseignantRep;
	private final EnseignantMapper enseignantMap;
	private final DepartementRepo departementRepo;
	@Override
	public EnseignantDto addEnseignant(EnseignantDto ens) {
		Enseignant enseignant=enseignantMap.dtoToEnseignant(ens);
		Departement departement= departementRepo.findByNameDeparetement(ens.getDepartementName()).orElseThrow(()->new DepartementNotFoundException("departement not found"));
		enseignant.setDepartement(departement);
		enseignant= enseignantRep.save(enseignant);
		return (EnseignantDto) enseignantMap.enseignantToDto(enseignant);
	}
	@Override
	public EnseignantDto updateEnseignant(String cin,EnseignantDto ens) {
		
		Enseignant enseignant=enseignantRep.findByCIN(cin);
        enseignant.setNom(ens.getNom());
        enseignant=enseignantRep.save(enseignant);
		return enseignantMap.enseignantToDto(enseignant);
	}
	@Override
	public void deleteEnseignant(Long id) {
		enseignantRep.deleteById(id);
		
	}
	@Override
	public List<EnseignantDto> getAllEns() {
		
		return enseignantMap.enseignantToDtos(enseignantRep.findAll());
	}
	@Override
	public EnseignantDto getEnsByCIN(String cin) {
		Enseignant enseignant=enseignantRep.findByCIN(cin);
	
		return enseignantMap.enseignantToDto(enseignant) ;
	}
	@Override
	public EnseignantDto getProfByEmail(String email) {
		Enseignant enseignant=enseignantRep.findByEmail(email);
		return enseignantMap.enseignantToDto(enseignant);				
		}
	@Override
	public EnseignantDto getProfById(Long id) {
		
		return enseignantMap.enseignantToDto(enseignantRep.findById(id).orElseThrow(()->new EnseignantNotFound("Enseignant not found"))) ;
	}
}
	


