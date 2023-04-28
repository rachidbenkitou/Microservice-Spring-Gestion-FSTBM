package com.enseignant.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourResponse {
	private long id_cour;
	private String intitule;
	private String dateDebut;
	private String dateUpdate;
	private long idModule;
	private long id_enseignant;
	private String moduleName;
	private String enseignant_name;
}
