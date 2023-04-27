package com.enseignant.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.enseignant.dto.DepartementDto;
import com.enseignant.entities.Departement;
import com.enseignant.request.DepartementRequest;
import com.enseignant.response.DepartementResponse;

@Mapper(componentModel = "spring",uses=Departement.class,injectionStrategy = InjectionStrategy.CONSTRUCTOR,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartementMapper {
	Departement dtoToDepartement(DepartementDto dto);
	DepartementDto departementToDto(Departement departement);
	List<DepartementDto> departementsToDtos(List<Departement> departements);

	DepartementDto requestToDepartementDto(DepartementRequest request);
	DepartementResponse departementDtoToResponse(DepartementDto departement);
	List<DepartementResponse> departementDtssToResponses(List<DepartementDto> departements);


}
