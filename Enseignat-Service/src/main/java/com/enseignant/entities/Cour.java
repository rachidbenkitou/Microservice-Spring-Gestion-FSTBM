package com.enseignant.entities;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cour {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_cour;
	private String intitule;
	private String documentPaht;
	private Date dateDebut;
	private Date dateUpdate;
	@OneToOne(fetch = FetchType.EAGER)
	private Enseignant enseignant;
	private Long idModule;
}
