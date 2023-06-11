package com.example.note2service.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Enseignant {
    private Integer id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String CIN;
    private Departement departement;
}
