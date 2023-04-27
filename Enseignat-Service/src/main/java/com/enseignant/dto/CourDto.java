package com.enseignant.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourDto {
	private long id_cour;
	private String intitule;
	private Date dateDebut;
	private Date dateUpdate;
	private Long idModule;
	private long id_enseignant;
}
