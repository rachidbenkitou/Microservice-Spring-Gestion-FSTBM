package com.example.etudiant1service;

import com.example.etudiant1service.config.RsaKeysConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.rsa.crypto.RsaAlgorithm;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)
public class Etudiant1ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Etudiant1ServiceApplication.class, args);
    }

}
