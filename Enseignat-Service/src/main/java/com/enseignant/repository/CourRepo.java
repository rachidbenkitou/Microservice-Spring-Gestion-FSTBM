package com.enseignant.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enseignant.entities.Cour;

public interface CourRepo extends JpaRepository<Cour, Long>{

	List<Cour> findByIntituleLike(String intitule,Pageable pageable);
	
	Cour findByIdModule(Long idModule);
	
	Cour findByEnseignantId(Long id);
	
	List<Cour> findByDateDebutBetween(Date date_debut1,Date date_debut2,Pageable pageable);
	
	
}
