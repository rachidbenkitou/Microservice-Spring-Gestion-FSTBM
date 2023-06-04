package com.example.etudaintinscriptionfiliereservice.dtos;

import com.example.etudaintinscriptionfiliereservice.entities.Inscription;
import com.example.etudaintinscriptionfiliereservice.entities.Niveau;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiliereResponseDto {
    private String id;
    private String name;
    private Niveau niveau;
    private List<Inscription> inscriptions;
}
