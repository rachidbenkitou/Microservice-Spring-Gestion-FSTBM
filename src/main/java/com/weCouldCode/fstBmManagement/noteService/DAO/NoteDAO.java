package com.weCouldCode.fstBmManagement.noteService.DAO;

import com.weCouldCode.fstBmManagement.noteService.Entities.Note;
import com.weCouldCode.fstBmManagement.noteService.Entities.NoteKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDAO extends JpaRepository<Note, NoteKey> {
}
