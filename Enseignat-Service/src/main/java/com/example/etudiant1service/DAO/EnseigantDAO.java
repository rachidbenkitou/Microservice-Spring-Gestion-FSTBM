package com.example.etudiant1service.DAO;

import com.example.etudiant1service.Entities.Enseigant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseigantDAO extends JpaRepository<Enseigant,Long> {
}
