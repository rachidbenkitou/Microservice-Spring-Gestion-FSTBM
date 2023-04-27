package com.example.note2service.DAO;

import com.example.note2service.Entities.Note;
import com.example.note2service.Entities.NoteKey;
import com.example.note2service.Entities.TypeExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDAO extends JpaRepository<Note, NoteKey> {
    @Query("SELECT n FROM Note n INNER JOIN n.examen e INNER JOIN e.module m  WHERE n.etudiantApogee=?1 AND e.type=?2 AND m.moduleName=?3")
    Note findByEtudiantNameAndTypeExamenAndNomModule(long etudiantApogee,TypeExamen typeExamen,String nomModule);
}
