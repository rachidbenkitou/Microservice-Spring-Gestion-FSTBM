package com.enseignant.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.Data;

@Configuration
@EnableGlobalMethodSecurity
@EnableWebSecurity
public class SecConfig {
	@Autowired
	private RsaKeysConfig rsakeysConfig;
	public SecConfig(RsaKeysConfig rsakeysConfig) {
		this.rsakeysConfig=rsakeysConfig;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain( HttpSecurity httpSecurity) throws Exception  {
		
		return httpSecurity.csrf(csrf->csrf.disable())
				.authorizeRequests(auth->auth.anyRequest().authenticated())
				.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
				.build();
		
	}
	JwtDecoder jwtDecoder() {
		 return NimbusJwtDecoder.withPublicKey(rsakeysConfig.publicKey()).build();
	}

}
