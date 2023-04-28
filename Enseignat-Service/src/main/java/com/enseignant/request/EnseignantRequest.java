package com.enseignant.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnseignantRequest {
	private String nom;
	private String prenom;

}
