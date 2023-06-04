package com.example.etudaintinscriptionfiliereservice.repositories;

import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    Etudiant findByApogee(Long apogee);

    List<Etudiant> findByInscription_FiliereId(String filiereId);

    Optional<Etudiant> findByCin(String cin);

}
