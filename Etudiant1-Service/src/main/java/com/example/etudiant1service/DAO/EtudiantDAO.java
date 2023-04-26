package com.example.etudiant1service.DAO;


import com.example.etudiant1service.Entities.Etudiant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantDAO extends JpaRepository<Etudiant,Long> {

    Etudiant findByApogee(long apogee);
}
