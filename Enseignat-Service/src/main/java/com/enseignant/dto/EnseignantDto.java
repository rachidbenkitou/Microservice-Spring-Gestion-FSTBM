package com.enseignant.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnseignantDto {
	private String nom;
	private String prenom;
	private String adresse;
	private String email;
	private String cin;
}
