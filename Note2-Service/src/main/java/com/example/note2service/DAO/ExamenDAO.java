package com.example.note2service.DAO;

import com.example.note2service.Entities.Examen;
import com.example.note2service.Entities.Note;
import com.example.note2service.Entities.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExamenDAO extends JpaRepository<Examen,Long> {
    @Query("SELECT e FROM Examen e INNER JOIN e.module m where m.moduleName = ?1  and m.moduleSemestre =?2")
    public List<Examen>  findExamenByModuleAndSemestre(@Param("key") String moduleName, Semestre moduleSemestre);
}
