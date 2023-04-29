package com.example.etudaintinscriptionfiliereservice.repositories;

import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import com.example.etudaintinscriptionfiliereservice.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, String> {
     boolean existsByEtudiant(Etudiant etudiant);
}
