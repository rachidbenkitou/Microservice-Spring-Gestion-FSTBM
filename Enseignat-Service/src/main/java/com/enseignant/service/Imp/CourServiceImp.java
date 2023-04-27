package com.enseignant.service.Imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.enseignant.dto.CourDto;
import com.enseignant.entities.Cour;
import com.enseignant.entities.Enseignant;
import com.enseignant.mapper.CourMapper;
import com.enseignant.repository.CourRepo;
import com.enseignant.request.Page;
import com.enseignant.service.CourService;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class CourServiceImp implements CourService {

	private final CourRepo courRepo;
	
	private final CourMapper courMapper;
	
	@Value("path.downloadFile")
	private String path;
	
	@Override
	public CourDto getCourById(Long id_cour) {

		Cour cour=courRepo.findById(id_cour).orElseThrow(null);
		CourDto courDto=courMapper.courToDto(cour);
		courDto.setId_enseignant(cour.getEnseignant().getId());
		return  courDto;
	}

	@Override
	public List<CourDto> getCoursHavingIntituleLike(String intitule, Page page) {
		List<Cour> cours=courRepo.findByIntituleLike(intitule, page.getPageRequest());
		return courMapper.coursToDtos(cours);
	}

	@Override
	public List<CourDto> getCoursByModuleIds(Long[] id_modules, Page page) {
		List<CourDto> courDtos= Stream.of(id_modules).map((id)->getCourByModuleId(id)).toList();
		return courDtos;
	}

	@Override
	public CourDto getCourByModuleId(Long id_module) {
		return courMapper.courToDto(courRepo.findByIdModule(id_module));
	}

	@Override
	public CourDto getCourByEnseignantId(Long id_enseign) {
		
		return courMapper.courToDto(courRepo.findByEnseignantId(id_enseign));
	}

	@Override
	public List<CourDto> getCoursBetweenDates(Date Date1, Date Date2, Page page) {
		
		return courMapper.coursToDtos(courRepo.findByDateDebutBetween(Date1, Date2, page.getPageRequest())) ;
	}

	@Override
	public InputStreamResource downloadDoducment(Long courId) throws FileNotFoundException {
		Cour cour=courRepo.findById(courId).orElseThrow();
		File file=new File(cour.getDocumentPaht()+""+cour.getIntitule()+"_"+cour.getId_cour());
		InputStreamResource resource= new InputStreamResource(
				new FileInputStream(file)
				);
		return resource;
	}

	@Override
	public List<CourDto> getAllCoursSortByDateUpdate(Page page) {
		List<Cour> cours= courRepo.findAll(PageRequest.of(page.getFirstElement(), page.getLastElement(), Sort.by("date_update"))).getContent();
		return courMapper.coursToDtos(cours);
	}

	@Override
	public String uploadDocument(Long id_cour, MultipartFile file) throws IOException {
		Cour cour=courRepo.findById(id_cour).orElseThrow();
		File convertfile= new File(path+""+cour.getIntitule()+"_"+cour.getId_cour());
		FileOutputStream fout=new FileOutputStream(convertfile);
		fout.write(file.getBytes());
		fout.close();
		cour.setDocumentPaht(path);
		cour.setDate_update(new Date());
		return "File is upload seccessfuly";
	}

	@Override
	public CourDto addCour(CourDto courDto) {
		Cour cour = courMapper.dtoTocour(courDto);
		//TODO getModuleInfo and getEnseignant white repoEnseign
		
		return courMapper.courToDto(courRepo.save(cour));
	}

	@Override
	public CourDto updateCour(Long idCour, CourDto courDto) {
		Cour cour=courRepo.findById(idCour).orElseThrow();
        if(courDto.getDateDebut()!=null) cour.setDateDebut(courDto.getDateDebut());
        if(courDto.getDate_update()!=null) cour.setDate_update(courDto.getDate_update());
        if(courDto.getId_enseignant()!=0) {
        	//TODO getEnseignatById
        	Enseignant enseignant=new Enseignant();
        	enseignant.setId(courDto.getId_enseignant());
        	cour.setEnseignant(enseignant);
        }
        if(courDto.getIdModule()!=0) cour.setIdModule(courDto.getIdModule());
        if(courDto.getIntitule()!=null) cour.setIntitule(courDto.getIntitule());
        courRepo.save(cour);
		return courMapper .courToDto(courRepo.save(cour)) ;
	}

	@Override
	public void deleteCour(Long idCour) {
		
		courRepo.deleteById(idCour);
		
	}

	
	
	
}
