package com.example.note2service.DAO;

import com.example.note2service.Entities.Note;
import com.example.note2service.Entities.NoteKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDAO extends JpaRepository<Note, NoteKey> {
}
