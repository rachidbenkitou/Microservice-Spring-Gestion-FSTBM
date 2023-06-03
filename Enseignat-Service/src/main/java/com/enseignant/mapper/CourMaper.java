package com.enseignant.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.enseignant.dto.CourDto;
import com.enseignant.entities.Cour;
import com.enseignant.request.CourRequest;
import com.enseignant.response.CourResponse;
@Mapper(componentModel = "spring",uses=Cour.class,injectionStrategy = InjectionStrategy.CONSTRUCTOR,unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface CourMaper {
	
	Cour dtoTocour(CourDto dto);
	CourDto courToDto(Cour cour);
	List<CourDto> coursToDtos(List<Cour> cours);

	
	CourDto requestTocourDto(CourRequest request);
	CourResponse courDtoToResponse(CourDto cour);
	List<CourResponse> courDtosToResponses(List<CourDto> cours);
	
	
}
