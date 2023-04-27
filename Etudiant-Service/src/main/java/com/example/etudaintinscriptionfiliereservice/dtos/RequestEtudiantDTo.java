package com.example.etudaintinscriptionfiliereservice.dtos;

import lombok.Data;

@Data
public class RequestEtudiantDTo {
    private long id;
    private long apogee;
    private String nom;
    private String prenom;
    private String lastname;
    private long tel;
    private String ville;
    private String adress;
}