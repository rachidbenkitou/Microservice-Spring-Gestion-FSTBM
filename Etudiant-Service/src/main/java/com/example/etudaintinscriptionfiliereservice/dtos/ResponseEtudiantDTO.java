package com.example.etudaintinscriptionfiliereservice.dtos;

import lombok.Data;

@Data
public class ResponseEtudiantDTO {

    private long id;
    private long apogee;
    private String firstname;
    private String lastname;
    private long tel;
    private String ville;
    private String adress;
}