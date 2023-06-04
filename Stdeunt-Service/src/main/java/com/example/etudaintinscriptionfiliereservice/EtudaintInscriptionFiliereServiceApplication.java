package com.example.etudaintinscriptionfiliereservice;

import com.example.etudaintinscriptionfiliereservice.config.RsakeyConfig;
import com.example.etudaintinscriptionfiliereservice.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(RsakeyConfig.class)
public class EtudaintInscriptionFiliereServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtudaintInscriptionFiliereServiceApplication.class, args);

    }

}
