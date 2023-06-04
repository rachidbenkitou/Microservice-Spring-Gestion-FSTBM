package com.example.note2service.DAO;

import com.example.note2service.Entities.Note;
import com.example.note2service.Entities.NoteKey;
import com.example.note2service.Entities.TypeExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface NoteDAO extends JpaRepository<Note, NoteKey> {
    //@Query("SELECT n FROM Note n INNER JOIN n.examen e INNER JOIN e.module m  WHERE n.etudiantApogee=?1 AND e.type=?2 AND m.moduleName=?3")
    //Note findByEtudiantNameAndTypeExamenAndNomModule(long etudiantApogee,TypeExamen typeExamen,String nomModule);

    @Query("SELECT n FROM Note n INNER JOIN n.examen e INNER JOIN e.module m where e.type=?1 and n.etudiantApogee=?2 and m.moduleName=?3")
    Note findByEtudiantNameAndTypeExamenAndNomModule(TypeExamen typeExamen, long etudiantApogee, String moduleName);

    @Query("select n from Note n where n.etudiantCin =?1")
    java.util.List<Note> findAllBycin(String cin);
    @Query("select n from Note n inner join n.examen e inner join e.module m where n.etudiantCin =?1 and m.moduleId =?2")
    java.util.List<Note> findAllByCinAndModuleId(String cin,long id);
}
