package com.enseignant.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.enseignant.entities.Module;
import com.enseignant.mapper.CourMaper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enseignant.request.CourRequest;
import com.enseignant.request.Page;
import com.enseignant.response.CourResponse;
import com.enseignant.service.CourService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
@RequestMapping("/cour")
public class CourController {

	private final CourService courService;
	
	private final CourMaper courMapper;
	
	@GetMapping("/id/{id_cour}")
	@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")

	 ResponseEntity<CourResponse>  getCourById(@PathVariable Long id_cour){
		return new ResponseEntity<CourResponse>(
				courMapper.courDtoToResponse(
						courService.getCourById(id_cour)
						),HttpStatus.OK);
	}
	@GetMapping("/search/intitile/{intitule}")
	@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")

	ResponseEntity<List<CourResponse>> getCoursHavingIntituleLike(@PathVariable String intitule,@RequestParam Integer  page,@RequestParam Integer nbrElement){
		return new ResponseEntity<List<CourResponse>>(
				courMapper.courDtosToResponses(
						courService.getCoursHavingIntituleLike(intitule, new Page(page,nbrElement) )
						),HttpStatus.OK);
	}
	@GetMapping("/module/id/{id_module}")
	@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
	ResponseEntity<CourResponse>  getCourByModuleId(@PathVariable Integer id_module){
		return new ResponseEntity<CourResponse>(
				courMapper.courDtoToResponse(
						courService.getCourByModuleId(id_module)
						),HttpStatus.OK);
	}
	//TODO test en postMan
	@GetMapping("/enseignant/id/{id_enseign}")
	@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
	ResponseEntity<CourResponse> getCourByEnseignantId(Long id_enseign) {
		return new ResponseEntity<CourResponse>(
				courMapper.courDtoToResponse(
						courService.getCourByEnseignantId(id_enseign)
						),HttpStatus.OK);
	}

	@GetMapping("enseignant/cin/{cin}")
	@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT')")

	ResponseEntity<CourResponse> getCourByEnseignantCin(@PathVariable String cin) {
		return new ResponseEntity<CourResponse>(
				courMapper.courDtoToResponse(
						courService.getCourByEnseignantCin(cin)
				),HttpStatus.OK);
	}

	@GetMapping("/search/bettewenDates")
	@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")

	ResponseEntity<List<CourResponse>>  getCoursBetweenDates(@RequestParam  String date1,@RequestParam  String date2,@RequestParam Integer  page,@RequestParam Integer nbrElement) throws ParseException{
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		return new ResponseEntity<List<CourResponse>>(
				courMapper.courDtosToResponses(
						courService.getCoursBetweenDates(formatter.parse(date1),formatter.parse(date2),  new Page(page,nbrElement))
						),HttpStatus.OK);
	}
	@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
	@GetMapping(value = "/downloadDodument/{courId}", consumes = MediaType.APPLICATION_PDF_VALUE)
	ResponseEntity<?>  downloadDoducment(@PathVariable Long courId,HttpServletResponse response) throws IOException{
		InputStream inputStream= courService.downloadDoducment(courId);


		
		IOUtils.copy(inputStream,response.getOutputStream());
	
		return  ResponseEntity.ok().build();
	}
	@GetMapping("/allSortByDateUpdate")
	@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
	ResponseEntity<List<CourResponse>>  getAllCoursSortByDateUpdate(@RequestParam Integer  page,@RequestParam Integer nbrElement){
		return new ResponseEntity<List<CourResponse>>(
				courMapper.courDtosToResponses(
						courService.getAllCoursSortByDateUpdate(new Page(page,nbrElement))
						),HttpStatus.OK);
	}
	@PostMapping(value = "uploadDocument/id/{id_cour}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PreAuthorize("hasAnyAuthority('SCOPE_ENSEIGNANT')")
	ResponseEntity<Map<String,String>> uploadDocument(@PathVariable Long id_cour,@RequestParam("file") MultipartFile file) throws IOException {
		return new ResponseEntity<Map<String,String>>(Map.of("message",courService.uploadDocument(id_cour, file)),HttpStatus.OK);
	}
	
	@PostMapping("/add/{cin}")
	@PreAuthorize("hasAnyAuthority('SCOPE_ENSEIGNANT')")
	ResponseEntity<CourResponse>  addCour(@RequestBody CourRequest cour,@PathVariable String cin){
		return new ResponseEntity<CourResponse>(
				courMapper.courDtoToResponse(
						courService.addCour(
								courMapper.requestTocourDto(cour),cin
								)
						),HttpStatus.OK);
	}
	@PutMapping("update/id/{idCour}")
	@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT')")
	ResponseEntity<CourResponse> updateCour(@PathVariable Long idCour,@RequestBody CourRequest courDto) {
		return new ResponseEntity<CourResponse>(
				courMapper.courDtoToResponse(
						courService.updateCour(idCour, 
								courMapper.requestTocourDto(courDto)
								)
						),HttpStatus.OK);
	}
	@DeleteMapping("delete/id/{idCour}")
	ResponseEntity<?> deleteCour(@PathVariable Long idCour) {
		
		courService.deleteCour(idCour);
		return new ResponseEntity<>(HttpStatus.OK);
	}


    @GetMapping("/module/enseignant/{cin}")
	@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ENSEIGNANT','SCOPE_ETUDIANT')")
	ResponseEntity<Module> getModuleByIdEnseigant(@PathVariable String cin){
        return  new ResponseEntity<>(courService.getModuleByIdEnseigant(cin),HttpStatus.OK);
    }
}
