package com.enseignant.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Page {

	private Integer firstElement;
	private Integer lastElement;
	
	public Pageable getPageRequest(){
		return PageRequest.of(firstElement, lastElement); 
	}
	
	
}
