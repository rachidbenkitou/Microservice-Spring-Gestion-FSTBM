package com.example.etudaintinscriptionfiliereservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEtudiantDto {
    private String id;
    private Long apogee;
    private String nom;
    private String prenom;
    private String email;
    private Date DateNaissance;
    private Long tel;
    private String ville;
    private String adress;
}