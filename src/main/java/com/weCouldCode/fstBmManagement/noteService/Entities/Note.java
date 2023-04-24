package com.weCouldCode.fstBmManagement.noteService.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {
    @EmbeddedId
    private NoteKey id ;
    private double note;
    private String mention;
    @ManyToOne
    @MapsId("examenId")
    @JoinColumn(name = "examen_id")
    private Examen examen;
    @Transient
    private Etudiant etudiant;
}
