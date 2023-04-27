package com.enseignant.service;

import java.util.List;

import com.enseignant.dto.DepartementDto;
import com.enseignant.entities.Enseignant;

public interface DepartementService {
	
	DepartementDto addDepartement(DepartementDto Dto);
	
	DepartementDto updateDepartement(long idD,DepartementDto Dto);
	
	void deleteDepartement(long idD);
	
	DepartementDto getDepartementById(long idD);

	DepartementDto getDeparementByName(String name);

	List<DepartementDto> getAllDeparetement();
	
	List<Enseignant> getProfsByNomeDepartment(String name);
	 
	
}
