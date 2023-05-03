package com.example.etudaintinscriptionfiliereservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record RsakeyConfig(RSAPublicKey publicKey) {

}
