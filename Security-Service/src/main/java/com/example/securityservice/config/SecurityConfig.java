package com.example.securityservice.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.PasswordLookup;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.FilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
     private RsaKeysConfig rsaKeysConfig;
    @Autowired
    private PasswordEncoder passwordEncoder;

     @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
                 var authenticationProvider =new DaoAuthenticationProvider();
                 authenticationProvider.setPasswordEncoder(passwordEncoder);
                 authenticationProvider.setUserDetailsService(userDetailsService);
                 return new ProviderManager(authenticationProvider);
    }
//     @Bean
//     public UserDetailsService inMemoryUserDetailsManager(){
//         return new InMemoryUserDetailsManager(
//                 User.withUsername("user1").password(passwordEncoder.encode("1234")).authorities("USER").build(),
//                 User.withUsername("user2").password(passwordEncoder.encode("1234")).authorities("USER").build(),
//                 User.withUsername("admin").password(passwordEncoder.encode("1234")).authorities("ADMIN","USER").build()
//         );
//     }
     @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
         return httpSecurity
                 .csrf(csrf->csrf.disable())
                 .authorizeHttpRequests(auth->auth.antMatchers("/token/**").permitAll())
                 .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
                 .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                 .build();
     }
     @Bean
     JwtEncoder jwtEncoder(){
         JWK jwk = new RSAKey.Builder(rsaKeysConfig.publicKey()).privateKey(rsaKeysConfig.privateKey()).build();
         JWKSource<SecurityContext>jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
         return new NimbusJwtEncoder(jwkSource);
     }
     @Bean
     JwtDecoder jwtDecoder(){
         return NimbusJwtDecoder.withPublicKey(rsaKeysConfig.publicKey()).build();
     }
}
