package com.enseignant.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;

import org.mapstruct.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.enseignant.dto.EnseignantDto;
import com.enseignant.entities.Enseignant;
import com.enseignant.request.EnseignantRequest;
import com.enseignant.response.DepartementResponse;
import com.enseignant.response.EnseignantResponse;

@Mapper(componentModel = "spring",uses=Enseignant.class,injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EnseignantMapper {
	
	Enseignant dtoToEnseignant(EnseignantDto dto);	
	List<EnseignantDto> enseignantToDtos(List<Enseignant> enseignant);
	EnseignantDto enseignantToDto( Enseignant enseignant);
	EnseignantResponse enseignantDtoToResponse(EnseignantDto profByEmail);
	
	List<EnseignantResponse> enseignantDtosToResponses(List<EnseignantDto> allEns);
	
	EnseignantDto requestToDepartementDto(EnseignantRequest request);
	EnseignantDto requestToEnseignantDto(EnseignantRequest request);
	

}
