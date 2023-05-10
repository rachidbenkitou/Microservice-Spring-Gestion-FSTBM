package com.enseignant;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.enseignant.configuration.RsaKeysConfig;
@EnableConfigurationProperties(RsaKeysConfig.class)
@SpringBootApplication
@EnableFeignClients
public class ServiceEnseignantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceEnseignantApplication.class, args);
	}
	



}
