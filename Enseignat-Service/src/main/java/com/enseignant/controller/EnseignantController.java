package com.enseignant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enseignant.mapper.EnseignantMapper;
import com.enseignant.request.EnseignantRequest;
import com.enseignant.response.EnseignantResponse;
import com.enseignant.service.EnseignantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/enseignant")
@RequiredArgsConstructor
public class EnseignantController {
	private final EnseignantService enseignantService;
	private final EnseignantMapper enseignantMap;
	
	 @GetMapping("/cin/{cin}")
	 @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
	 ResponseEntity<EnseignantResponse>getEnseignantByCIN(@PathVariable String cin){
		 return new ResponseEntity<EnseignantResponse>(
					enseignantMap.enseignantDtoToResponse(enseignantService.getEnsByCIN(cin)) ,
					HttpStatus.OK);
	 }
	 @GetMapping("/email/{email}")
	 @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")

	 ResponseEntity<EnseignantResponse> getDeparementByName(@PathVariable String email){
			return new ResponseEntity<EnseignantResponse>(
					enseignantMap.enseignantDtoToResponse(enseignantService.getProfByEmail(email)),
					HttpStatus.OK);
	

}
	 @GetMapping("/all")
	 @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")

	 ResponseEntity<List<EnseignantResponse>> getAllEnseignant(){
			return new ResponseEntity<List<EnseignantResponse>>(
					enseignantMap.enseignantDtosToResponses(enseignantService.getAllEns()),
					HttpStatus.OK);
}
		@PostMapping("/add")
		@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")

		ResponseEntity<EnseignantResponse> addEnseignant(@RequestBody EnseignantRequest request){
			return new ResponseEntity<EnseignantResponse>(
					enseignantMap.enseignantDtoToResponse(enseignantService.addEnseignant(
							enseignantMap.requestToDepartementDto(request)))  ,
					        HttpStatus.OK);
		}
		@PutMapping("/update/cin/{cin}")
		@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")

		ResponseEntity<EnseignantResponse> updateEnseignant(@PathVariable String cin,@RequestBody EnseignantRequest request){
			return new ResponseEntity<EnseignantResponse>(
					enseignantMap.enseignantDtoToResponse(enseignantService.updateEnseignant(cin,
						            	enseignantMap.requestToEnseignantDto(request))),
					                    HttpStatus.OK);
		}
		
		@DeleteMapping("delete/id/{idD}")
		@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
		ResponseEntity<?> deleteDepartement(@PathVariable long idD){
			enseignantService.deleteEnseignant(idD);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
}