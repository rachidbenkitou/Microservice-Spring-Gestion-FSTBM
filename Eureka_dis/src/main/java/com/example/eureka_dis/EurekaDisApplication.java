package com.example.eureka_dis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaDisApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaDisApplication.class, args);
	}

}
