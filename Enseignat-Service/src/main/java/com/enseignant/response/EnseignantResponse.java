package com.enseignant.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnseignantResponse {
	private Integer id;
	private String nom;
	private String prenom;
	private String adresse;
	private String email;
	private String CIN;
	private DepartementResponse departement;

}
