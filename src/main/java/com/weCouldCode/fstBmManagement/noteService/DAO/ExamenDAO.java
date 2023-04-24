package com.weCouldCode.fstBmManagement.noteService.DAO;


import com.weCouldCode.fstBmManagement.noteService.Entities.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenDAO extends JpaRepository<Examen,Long> {
}
