package com.enseignant.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enseignant.entities.Cour;

public interface CourRepo extends JpaRepository<Cour, Long>{

	Optional<List<Cour>> findByIntituleLike(String intitule,Pageable pageable);
	
	Optional<Cour> findByIdModule(Integer idModule);
	
	Optional<Cour> findByEnseignantId(Long id);
	
	Optional<List<Cour>> findByDateDebutBetween(Date date_debut1,Date date_debut2,Pageable pageable);
	
	
}
