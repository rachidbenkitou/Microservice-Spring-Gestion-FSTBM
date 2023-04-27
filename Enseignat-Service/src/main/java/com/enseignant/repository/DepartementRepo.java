package com.enseignant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enseignant.entities.Departement;

public interface DepartementRepo extends JpaRepository<Departement, Long>{

	Optional<Departement> findByNameDeparetement(String nameDeparetement);
	boolean existsByNameDeparetement(String nameDeparetement);
	
}
