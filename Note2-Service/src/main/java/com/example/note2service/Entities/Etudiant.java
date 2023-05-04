package com.example.note2service.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Etudiant {

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
