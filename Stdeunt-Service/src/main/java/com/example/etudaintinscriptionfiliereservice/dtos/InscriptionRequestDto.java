package com.example.etudaintinscriptionfiliereservice.dtos;

import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import com.example.etudaintinscriptionfiliereservice.entities.Filiere;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscriptionRequestDto {
    private String idInscription;
    private Date dateInscripton;
    private Filiere filiere;
    private RequestEtudiantDto requestEtudiantDto;
}
