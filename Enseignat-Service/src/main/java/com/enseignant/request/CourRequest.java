package com.enseignant.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourRequest {
	private String intitule;
	private String dateDebut;
	private String date_update;
	private long idModule;
	private long id_enseignant;
}
