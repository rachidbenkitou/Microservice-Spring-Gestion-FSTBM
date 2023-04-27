package com.example.etudaintinscriptionfiliereservice.repositories;

import com.example.etudaintinscriptionfiliereservice.entities.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiliereRepository extends JpaRepository<Filiere, String> {
    Filiere findByName(String name);
}
