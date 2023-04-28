package com.enseignant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceEnseignantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceEnseignantApplication.class, args);
	}

}
