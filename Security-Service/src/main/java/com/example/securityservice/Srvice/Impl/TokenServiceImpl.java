package com.example.securityservice.Srvice.Impl;

import com.example.securityservice.DAO.UserDAO;
import com.example.securityservice.DTO.TokenDto;
import com.example.securityservice.Entities.User;
import com.example.securityservice.Srvice.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class TokenServiceImpl implements TokenService {

    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private UserDAO userDAO;
    @Autowired
    public TokenServiceImpl(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService,UserDAO userDAO) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userDAO = userDAO;
    }

    @Override
    public Map<String, String> jwToken(TokenDto tokenDto) {
        String subject =null;
        String scope = null;
        Map<String,String> idToken = new HashMap<>();
        if (tokenDto.getGrantType().equals("password")){
            Authentication authentication=  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(tokenDto.getUsername(),tokenDto.getPassword())
            );
            subject=authentication.getName();
            scope = authentication.getAuthorities()
                    .stream().map(auth->auth.getAuthority()).collect(Collectors.joining(" "));
            User user = userDAO.findByEmail(tokenDto.getUsername()).get();
            idToken.put("cin",user.getCin());

        } else if (tokenDto.getGrantType().equals("refreshToken")) {
            Jwt decodeJwt =jwtDecoder.decode(tokenDto.getRefreshToken());
            subject =decodeJwt.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            scope = authorities.stream().map(auth->auth.getAuthority()).collect(Collectors.joining(" "));

        }

        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet  = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(tokenDto.getWithRefreshToken()?5:30, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope",scope)
                .build();
        String jwtAccesToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idToken.put("accessToken",jwtAccesToken);

        if (tokenDto.getWithRefreshToken()){
            JwtClaimsSet jwtClaimsSetRefresh  = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
                    .issuer("security-service")
                    .build();
            String jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
            idToken.put("refreshToken",jwtRefreshToken);
        }


        return idToken;
    }
}
