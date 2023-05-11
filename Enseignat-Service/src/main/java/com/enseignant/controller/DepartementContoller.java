package com.enseignant.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enseignant.entities.Enseignant;
import com.enseignant.mapper.DepartementMapper;
import com.enseignant.request.DepartementRequest;
import com.enseignant.response.DepartementResponse;
import com.enseignant.service.DepartementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departement")
public class DepartementContoller {

	private final DepartementService departementService;
	
	private final DepartementMapper departementMapper;
	
	
	@GetMapping("/id/{id}")
	ResponseEntity<DepartementResponse> getDepartementById(@PathVariable long idD){
		return new ResponseEntity<DepartementResponse>(
				departementMapper.departementDtoToResponse(departementService.getDepartementById(idD)) ,
				HttpStatus.OK);
	}
	@GetMapping("/name/{name}")
	ResponseEntity<DepartementResponse> getDeparementByName(@PathVariable String name){
		return new ResponseEntity<DepartementResponse>(
				departementMapper.departementDtoToResponse(departementService.getDeparementByName(name)),
				HttpStatus.OK);
	}
	@GetMapping("/all")
	ResponseEntity<List<DepartementResponse>> getAllDeparetement(){
		return new ResponseEntity<List<DepartementResponse>>(
				departementMapper.departementDtssToResponses(departementService.getAllDeparetement()),
				HttpStatus.OK);
	}
	@GetMapping("/name/all")
	ResponseEntity<Set<String>> getAllDeparetementNames(){
		return new ResponseEntity<Set<String>>(
				departementService.getAllDeparetementNames(),
				HttpStatus.OK);
	}
	@GetMapping("/profsByname/{name}")
	ResponseEntity<List<Enseignant>> getProfsByNomeDepartment(@PathVariable String name){
		return new ResponseEntity<List<Enseignant>>(
				departementService.getProfsByNomeDepartment(name),
				HttpStatus.OK);
	}
	@PostMapping("/add")
	ResponseEntity<DepartementResponse> addDepartement(@RequestBody DepartementRequest request){
		return new ResponseEntity<DepartementResponse>(
				departementMapper.departementDtoToResponse(departementService.addDepartement(
						departementMapper.requestToDepartementDto(request)))  ,
				        HttpStatus.OK);
	}
	
	@PutMapping("/update/id/{id}")
	ResponseEntity<DepartementResponse> updateDepartement(@PathVariable long id,@RequestBody DepartementRequest request){
		return new ResponseEntity<DepartementResponse>(
				departementMapper.departementDtoToResponse(departementService.updateDepartement(id,
					            	departementMapper.requestToDepartementDto(request))),
				                    HttpStatus.OK);
	}
	
	@DeleteMapping("delete/id/{idD}")
	ResponseEntity<?> deleteDepartement(@PathVariable long idD){
		departementService.deleteDepartement(idD);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
}
