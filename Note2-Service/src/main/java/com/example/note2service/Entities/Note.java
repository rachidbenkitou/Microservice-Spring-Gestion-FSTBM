package com.example.note2service.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Examen examen;
    @Transient
    private  Etudiant etudiant;
}
