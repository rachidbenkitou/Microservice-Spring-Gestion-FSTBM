package com.enseignant.service.Imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.enseignant.entities.Module;
import com.enseignant.mapper.CourMaper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.enseignant.dto.CourDto;
import com.enseignant.entities.Cour;
import com.enseignant.entities.Enseignant;
import com.enseignant.exeption.CourNoteFoundException;
import com.enseignant.exeption.EnseignantNotFound;
import com.enseignant.exeption.ModuleAlrealyHasCour;
import com.enseignant.exeption.ModuleNotFound;
import com.enseignant.openFeign.ModuleFeignClient;
import com.enseignant.repository.CourRepo;
import com.enseignant.repository.EnseignantRepo;
import com.enseignant.request.Page;
import com.enseignant.service.CourService;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class CourServiceImp implements CourService {

	private final CourRepo courRepo;
	
	private final EnseignantRepo enseignantRepo;
	
	private final CourMaper courMapper;
	
	private final ModuleFeignClient moduleFeignClient; 
	
	@Value("${path.downloadFile}")
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
		List<Cour> cours=courRepo.findByIntituleLike("%"+intitule+"%", page.getPageRequest())
				.orElseThrow(()->new CourNoteFoundException("cours like "+intitule+"not found"));
		return courMapper.coursToDtos(cours);
	}

	@Override
	public CourDto getCourByModuleId(Integer id_module) {
		return courMapper.courToDto(courRepo.findByIdModule(id_module)
				.orElseThrow(()->new CourNoteFoundException("cours found")));
	}

	@Override
	public CourDto getCourByEnseignantId(Long id_enseign) {
		
		return courMapper.courToDto(courRepo.findByEnseignantId(id_enseign)
				.orElseThrow(()->new CourNoteFoundException("cours found")));
	}

	@Override
	public List<CourDto> getCoursBetweenDates(Date Date1, Date Date2, Page page) {
		
		return courMapper.coursToDtos(courRepo.findByDateDebutBetween(Date1, Date2, page.getPageRequest())
				.orElseThrow(()->new CourNoteFoundException("cours found")));
				
	}

	@Override
	public InputStream downloadDoducment(Long courId) throws FileNotFoundException {
		Cour cour=courRepo.findById(courId).orElseThrow();
		File file=new File(cour.getDocumentPaht(),cour.getIntitule()+"_"+cour.getId_cour());
		
		InputStream  inputStream=new FileInputStream(file);
		return inputStream;
	}

	@Override
	public List<CourDto> getAllCoursSortByDateUpdate(Page page) {

		List<Cour> cours = courRepo.findAll(PageRequest.of(page.getPage(), page.getNbrElemet(), Sort.by("DateUpdate"))).getContent();
		List<Module> modules = moduleFeignClient.getModulesByIds(
				cours.stream().map((cour -> cour.getIdModule())).
				toList()).orElseThrow(() -> new ModuleNotFound("modules not found"));
		List<CourDto> dtos = courMapper.coursToDtos(cours);
		dtos= dtos.stream().map((dto)->{
			Enseignant enseignant= cours.stream()
					.filter((cour) -> cour.getId_cour() == dto.getId_cour()).toList().get(0).getEnseignant();
			dto.setId_enseignant(enseignant.getId());
			dto.setEnseignant_name(enseignant.getPrenom() + " "+ enseignant.getNom());
			return dto;
		}).toList();
		return dtos.stream().map((cour) -> {
			cour.setModuleName(modules.stream()
					.filter((module) -> module.getModuleId() == cour.getIdModule()).toList().get(0).getModuleName());
			return cour;
		}).toList();


	}
	@Override
	public String uploadDocument(Long id_cour, MultipartFile file) throws IOException {
		Cour cour=courRepo.findById(id_cour).orElseThrow();
		File convertfile= new File(path,cour.getIntitule()+"_"+cour.getId_cour());
		FileOutputStream fout=new FileOutputStream(convertfile);
		fout.write(file.getBytes());
		fout.close();

		cour.setDocumentPaht(path);
		cour.setDateUpdate(new Date());
		return "File is upload seccessfuly";
	}
	@CircuitBreaker(name = "enseignantc" , fallbackMethod = "fallbackSaveOrUpdateCour")
	@Override
	public CourDto addCour(CourDto courDto) {
		Cour cour = courMapper.dtoTocour(courDto);

		if(courRepo.findByIdModule(courDto.getIdModule()).isPresent()) throw new ModuleAlrealyHasCour("this module has a cour");
		System.out.println(cour);
		com.enseignant.entities.Module module=moduleFeignClient.getModuleById(courDto.getIdModule()).orElseThrow(()-> new ModuleNotFound("module not found"));
		Enseignant enseignant=enseignantRepo.findById(courDto.getId_enseignant()).orElseThrow(()->new EnseignantNotFound("enseignant not found"));
		if(enseignant.getCour()!=null) throw
				new ModuleAlrealyHasCour("prof has alrealy cour");
		cour.setDateUpdate(new Date());
		cour.setEnseignant(enseignant);

		CourDto courDtoSave= courMapper.courToDto(courRepo.save(cour));
		System.out.println(courDtoSave);
		courDtoSave.setModuleName(module.getModuleName());
		courDtoSave.setEnseignant_name(enseignant.getNom()+" "+enseignant.getPrenom());
		courDtoSave.setId_enseignant(enseignant.getId());
		return courDtoSave;
	}
	@CircuitBreaker(name = "enseignantc" , fallbackMethod = "fallbackSaveOrUpdateCour")
	@Override
	public CourDto updateCour(Long idCour, CourDto courDto) {
		Cour cour=courRepo.findById(idCour).orElseThrow();
        if(courDto.getDateDebut()!=null) cour.setDateDebut(courDto.getDateDebut());
        if(courDto.getDateUpdate()!=null) cour.setDateUpdate(courDto.getDateUpdate());
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
	public CourDto fallbackSaveOrUpdateCour(Exception e){
		// Log the error
		System.out.println("U cant add or update  any cours now");
		Cour cour =null;
		return courMapper.courToDto(cour);
	}

	@Override
	public void deleteCour(Long idCour) {
		
		courRepo.deleteById(idCour);
		
	}

	@Override
	public Module getModuleByIdEnseigant(String cin) {
		Enseignant enseignant=enseignantRepo.findByCIN(cin);
		Cour cour= courRepo.findByEnseignantId(enseignant.getId()).get();

		return moduleFeignClient.getModuleById(cour.getIdModule()).get();
	}


}
