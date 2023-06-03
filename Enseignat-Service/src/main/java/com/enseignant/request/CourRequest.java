package com.enseignant.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourRequest {
	private String intitule;
	private Date dateDebut;
	private Date date_update;
	private Integer idModule;
	private long id_enseignant;
}
