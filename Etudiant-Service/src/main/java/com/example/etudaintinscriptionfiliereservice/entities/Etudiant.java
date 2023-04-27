package com.example.etudaintinscriptionfiliereservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private long apogee;
    private String nom;
    private String prenom;
    private String email;
    private Date dateNaissance;
    private long tel;
    private String ville;
    private String adress;
    //@OneToOne(mappedBy = "etudiant")
   // private List<Inscription> inscriptionList;

}