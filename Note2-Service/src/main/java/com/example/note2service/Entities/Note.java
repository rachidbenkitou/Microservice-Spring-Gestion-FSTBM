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
    private double oldNote;
    private String mention;
    @ManyToOne
    @MapsId("examenId")
    @JoinColumn(name = "examen_id")
    private Examen examen;
    private long etudiantApogee;
    private String etudiantCin;
    @Transient
    private  Etudiant etudiant;
}
