package com.example.etudaintinscriptionfiliereservice.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Inscription {
    @Id
    private String idInscription;
    private Date dateInscripton;
    @ManyToOne
    private Filiere filiere;

}
