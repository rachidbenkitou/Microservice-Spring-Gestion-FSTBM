package com.enseignant.openFeign;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.enseignant.entities.Module;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "note-service",url = "http://localhost:8222/note-service/api/v1")
public interface ModuleFeignClient {
	
	@GetMapping("modules/moduleId/{moduleId}")
	Optional<Module> getModuleById(@PathVariable Integer moduleId);

	@PostMapping("modules/moduleIds")
	Optional<List<Module>> getModulesByIds(@RequestBody List<Integer> moduleIds);


}
