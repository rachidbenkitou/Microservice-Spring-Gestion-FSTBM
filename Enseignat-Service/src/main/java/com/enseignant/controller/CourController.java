package com.enseignant.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.enseignant.mapper.CourMapper;
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
	
	private final CourMapper courMapper;
	
	@GetMapping("/id/{id_cour}")
	ResponseEntity<CourResponse>  getCourById(@PathVariable Long id_cour){
		return new ResponseEntity<CourResponse>(
				courMapper.courDtoToResponse(
						courService.getCourById(id_cour)
						),HttpStatus.OK);
	}
	@GetMapping("/search/intitile/{intitule}?first={first},last={last}")
	ResponseEntity<List<CourResponse>> getCoursHavingIntituleLike(@PathVariable String intitule,@PathVariable Integer  first,@PathVariable Integer last) {
		return new ResponseEntity<List<CourResponse>>(
				courMapper.courDtosToResponses(
						courService.getCoursHavingIntituleLike(intitule, new Page(first,last) )
						),HttpStatus.OK);
	}
	@GetMapping("/modules?id_modules={id_modules}")
	ResponseEntity<List<CourResponse>>  getCoursByModuleIds(Long id_modules[],Integer  first,Integer last){
		return new ResponseEntity<List<CourResponse>>(
				courMapper.courDtosToResponses(
						courService.getCoursByModuleIds(id_modules, new Page(first,last))
						),HttpStatus.OK);
	}
	@GetMapping("/module?id={id_module}")
	ResponseEntity<CourResponse>  getCourByModuleId(Long id_module){
		return new ResponseEntity<CourResponse>(
				courMapper.courDtoToResponse(
						courService.getCourByModuleId(id_module)
						),HttpStatus.OK);
	}
	@GetMapping("/enseignant/id/{id_enseign}")
	ResponseEntity<CourResponse> getCourByEnseignantId(Long id_enseign) {
		return new ResponseEntity<CourResponse>(
				courMapper.courDtoToResponse(
						courService.getCourByEnseignantId(id_enseign)
						),HttpStatus.OK);
	}
	@GetMapping("/search/bettewenDates?date1={date1},date2={date2},first={first},last={last}")
	ResponseEntity<List<CourResponse>>  getCoursBetweenDates(Date Date1,Date Date2,Integer  first,Integer last){
		return new ResponseEntity<List<CourResponse>>(
				courMapper.courDtosToResponses(
						courService.getCoursBetweenDates(Date1, Date2,  new Page(first,last))
						),HttpStatus.OK);
	}
	@GetMapping("/downloadDodument/{courId}")
	ResponseEntity<?>  downloadDoducment(Long courId) throws IOException{
		 InputStreamResource inputStreamResource= courService.downloadDoducment(courId);
		HttpHeaders headers=new HttpHeaders();
		headers.add("Contenet-Disposition",String.format("attachementfilename=\"%s\"", inputStreamResource.getFilename()));
		headers.add("Cache-Control", "no-cachhe, no-store, must-revalide");
		headers.add("Expires", "0");
		
		return  ResponseEntity.ok().headers(headers)
				.contentLength(inputStreamResource.contentLength())
				.contentType(MediaType.parseMediaType("application/txt"))
				.body(inputStreamResource);
	}
	@GetMapping("/allSortByDateUpdate?first={first},last={last}")
	ResponseEntity<List<CourResponse>>  getAllCoursSortByDateUpdate(@RequestParam Integer  first,@RequestParam Integer last){
		return new ResponseEntity<List<CourResponse>>(
				courMapper.courDtosToResponses(
						courService.getAllCoursSortByDateUpdate(new Page(first,last))
						),HttpStatus.OK);
	}
	@PostMapping(value = "uploadDocument",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseEntity<String> uploadDocument(Long id_cour,@RequestParam("file") MultipartFile file) throws IOException {
		return new ResponseEntity<String>(courService.uploadDocument(id_cour, file),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	ResponseEntity<CourResponse>  addCour(@RequestBody CourRequest courDto){
		return new ResponseEntity<CourResponse>(
				courMapper.courDtoToResponse(
						courService.addCour(
								courMapper.requestTocourDto(courDto)
								)
						),HttpStatus.OK);
	}
	@PutMapping("update/id/{idCour}")
	ResponseEntity<CourResponse> updateCour(@PathVariable Long idCour,@RequestBody CourRequest courDto) {
		return new ResponseEntity<CourResponse>(
				courMapper.courDtoToResponse(
						courService.updateCour(idCour, 
								courMapper.requestTocourDto(courDto)
								)
						),HttpStatus.OK);
	}
	@DeleteMapping("delete/id/{idCour}")
	ResponseEntity<?> deleteCour(Long idCour) {
		courService.deleteCour(idCour);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
