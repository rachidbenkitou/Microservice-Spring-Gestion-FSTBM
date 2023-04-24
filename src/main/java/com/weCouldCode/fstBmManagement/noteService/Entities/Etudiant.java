package com.weCouldCode.fstBmManagement.noteService.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Etudiant {
    private long id;
    private long apogee;
    private String firstname;
    private String lastname;
    private long tel;
    private String ville;
    private String adress;
}
