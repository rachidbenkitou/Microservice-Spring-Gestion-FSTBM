package com.enseignant.service;

import java.util.List;

import com.enseignant.dto.EnseignantDto;


public interface EnseignantService {
	public EnseignantDto addEnseignant(EnseignantDto ens);
	public EnseignantDto updateEnseignant(String cin, EnseignantDto ens);
	public void deleteEnseignant(Long  id );
	public List<EnseignantDto> getAllEns();
	public EnseignantDto getEnsByCIN( String cin);
	public EnseignantDto getProfByEmail(String email);
	public EnseignantDto getProfById(Long id);
	 

}
