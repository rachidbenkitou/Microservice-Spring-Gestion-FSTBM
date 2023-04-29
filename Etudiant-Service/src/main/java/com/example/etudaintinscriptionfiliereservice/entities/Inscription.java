package com.example.etudaintinscriptionfiliereservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inscription {
    @Id
    private String idInscription;
    private Date dateInscripton;
    @ManyToOne
    @JsonIgnore
    private Filiere filiere;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id")
    @JsonIgnore
    private Etudiant etudiant;
}
