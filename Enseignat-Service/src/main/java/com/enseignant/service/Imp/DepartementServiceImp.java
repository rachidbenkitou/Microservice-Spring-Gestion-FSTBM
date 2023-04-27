package com.enseignant.service.Imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enseignant.dto.DepartementDto;
import com.enseignant.entities.Departement;
import com.enseignant.entities.Enseignant;
import com.enseignant.exeption.DepartementAlreadyExist;
import com.enseignant.exeption.DepartementNotFoundException;
import com.enseignant.mapper.DepartementMapper;
import com.enseignant.repository.DepartementRepo;
import com.enseignant.service.DepartementService;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartementServiceImp implements DepartementService {
	
	private final DepartementRepo departementRepo;
	
	private final DepartementMapper departementMapper;
	
	

	@Override
	public DepartementDto addDepartement(DepartementDto Dto) {
		Departement departement=departementMapper.dtoToDepartement(Dto);
		if(departementRepo.existsByNameDeparetement(departement.getNameDeparetement()))throw new DepartementAlreadyExist("departement "+departement.getNameDeparetement()+"already exist ");
		departement=departementRepo.save(departement);
		return departementMapper.departementToDto(departement);
	}

	@Override
	public DepartementDto updateDepartement(long idD, DepartementDto Dto) {
        Departement departement=departementRepo.findById(idD).orElseThrow(()->new DepartementNotFoundException("Departement not found"));
        departement.setNameDeparetement(Dto.getNameDeparetement());
        departement=departementRepo.save(departement);
		return departementMapper.departementToDto(departement);
	}

	@Override
	public void deleteDepartement(long idD) {
		
		departementRepo.deleteById(idD);
	}

	@Override
	public DepartementDto getDepartementById(long idD) {
           Departement departement=departementRepo.findById(idD).orElseThrow(()->new DepartementNotFoundException("Departement not found"));
		return departementMapper.departementToDto(departement);
	}

	@Override
	public DepartementDto getDeparementByName(String name) {
		Departement departement= departementRepo.findByNameDeparetement(name).orElseThrow(()->
		               new DepartementNotFoundException("departement not found"));
		return departementMapper.departementToDto(departement);
	}

	@Override
	public List<DepartementDto> getAllDeparetement() {
		
		return departementMapper.departementsToDtos(departementRepo.findAll());
	}

	@Override
	// TODO Enseignant dto
	public List<Enseignant> getProfsByNomeDepartment(String name) {
		Departement departement=departementRepo.findByNameDeparetement(name).orElseThrow(()->new DepartementNotFoundException("departement not found"));
		return departement.getEnseignants();
	}

}
