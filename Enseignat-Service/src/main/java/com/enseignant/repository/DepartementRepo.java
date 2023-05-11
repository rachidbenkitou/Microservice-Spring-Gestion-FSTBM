package com.enseignant.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.enseignant.entities.Departement;

public interface DepartementRepo extends JpaRepository<Departement, Long>{

	Optional<Departement> findByNameDeparetement(String nameDeparetement);
	boolean existsByNameDeparetement(String nameDeparetement);
	@Query("select d.nameDeparetement from Departement d")
	Optional<Set<String>> findNameDeparetements();
	
}
