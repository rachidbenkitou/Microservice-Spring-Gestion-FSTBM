package com.example.etudaintinscriptionfiliereservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {
    @Id
    private String id;
    @Column(unique = true)
    private Long apogee;
    @Column(unique = true)
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private Date dateNaissance;
    private Long tel;
    private String ville;
    private String adress;
    @OneToOne(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Inscription inscription;
}