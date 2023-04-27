package com.example.note2service.DAO;

import com.example.note2service.Entities.Examen;
import com.example.note2service.Entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenDAO extends JpaRepository<Examen,Long> {

}
