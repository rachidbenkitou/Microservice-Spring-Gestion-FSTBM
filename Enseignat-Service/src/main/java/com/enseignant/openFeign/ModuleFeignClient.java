package com.enseignant.openFeign;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.enseignant.entities.Module;

@FeignClient(name = "customer-service")
public interface ModuleFeignClient {
	
	@GetMapping("/module/{id}")
	Optional<Module> getModuleById(@PathVariable Long id);

}
