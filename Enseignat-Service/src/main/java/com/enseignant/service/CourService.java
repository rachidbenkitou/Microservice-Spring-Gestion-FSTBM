package com.enseignant.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.enseignant.entities.Module;
import org.springframework.web.multipart.MultipartFile;

import com.enseignant.dto.CourDto;
import com.enseignant.request.Page;

public interface CourService {
	
	CourDto getCourById(Long id_cour);
	List<CourDto> getCoursHavingIntituleLike(String intitule,Page page);
	CourDto getCourByModuleId(Integer id_module);
	CourDto getCourByEnseignantId(Long id_enseign);

	CourDto getCourByEnseignantCin(String cin);
	List<CourDto> getCoursBetweenDates(Date Date1,Date Date2,Page page);
	InputStream downloadDoducment(Long courId) throws FileNotFoundException ;
	List<CourDto> getAllCoursSortByDateUpdate(Page page);
	String uploadDocument(Long id_cour,MultipartFile file)throws IOException ;
	CourDto addCour(CourDto courDto,String cin);
	CourDto updateCour(Long idCour,CourDto courDto);
    void deleteCour(Long idCour);

	Module getModuleByIdEnseigant(String cin);
    
	

}
