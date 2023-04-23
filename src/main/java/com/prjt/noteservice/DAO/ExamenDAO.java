package com.prjt.noteservice.DAO;

import com.prjt.noteservice.Entities.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenDAO extends JpaRepository<Examen,Long> {
}
