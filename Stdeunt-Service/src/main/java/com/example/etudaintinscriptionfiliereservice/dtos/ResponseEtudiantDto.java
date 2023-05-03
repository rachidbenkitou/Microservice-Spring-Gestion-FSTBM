package com.example.etudaintinscriptionfiliereservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEtudiantDto {

    private String id;
    private Long apogee;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private Date dateNaissance;
    private Long tel;
    private String ville;
    private String adress;


}