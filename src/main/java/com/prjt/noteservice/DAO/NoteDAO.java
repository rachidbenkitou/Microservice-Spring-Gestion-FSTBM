package com.prjt.noteservice.DAO;

import com.prjt.noteservice.Entities.Note;
import com.prjt.noteservice.Entities.NoteKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDAO extends JpaRepository<Note, NoteKey> {
}
