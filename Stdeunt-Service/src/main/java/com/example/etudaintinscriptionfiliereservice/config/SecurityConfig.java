
package com.example.etudaintinscriptionfiliereservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {
    private RsakeyConfig rsakeyConfig;

    public SecurityConfig(RsakeyConfig rsakeyConfig) {
        this.rsakeyConfig = rsakeyConfig;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->auth.anyRequest().permitAll())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();
        /*.csrf(csrf->csrf.disable())
                .authorizeRequests(auth->auth.antMatchers("/swagger-ui/**").permitAll())
                //.authorizeRequests(auth->auth.anyRequest().authenticated())
                .authorizeRequests(auth->auth.anyRequest().permitAll())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();*/
    }
    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsakeyConfig.publicKey()).build();
    }
}


