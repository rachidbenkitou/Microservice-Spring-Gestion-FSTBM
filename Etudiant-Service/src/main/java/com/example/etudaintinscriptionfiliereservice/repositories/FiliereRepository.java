package com.example.etudaintinscriptionfiliereservice.repositories;

import com.example.etudaintinscriptionfiliereservice.entities.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FiliereRepository extends JpaRepository<Filiere, String> {
    Optional<Filiere> findByName(String name);
}
