package com.enseignant.openFeign;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.enseignant.entities.Module;

@FeignClient(name = "note",url = "http://localhost:8222/api/note-service")
public interface ModuleFeignClient {
	
	@GetMapping("modules/moduleId/{moduleId}")
	Optional<Module> getModuleById(@PathVariable Long moduleId);

}
