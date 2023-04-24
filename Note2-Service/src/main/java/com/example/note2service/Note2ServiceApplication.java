package com.example.note2service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Note2ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Note2ServiceApplication.class, args);
	}

}
