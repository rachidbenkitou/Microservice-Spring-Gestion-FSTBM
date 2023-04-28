package com.enseignant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enseignant.dto.EnseignantDto;
import com.enseignant.entities.Enseignant;
@Repository
public interface EnseignantRepo extends JpaRepository<Enseignant, Long> {
	Enseignant findByCIN(String cin);
	Enseignant findByEmail(String email);
	
	

	

}
