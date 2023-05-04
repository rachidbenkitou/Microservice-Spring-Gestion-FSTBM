package com.example.note2service;

import com.example.note2service.config.RsaKeysConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(RsaKeysConfig.class)
public class Note2ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Note2ServiceApplication.class, args);
	}

}
