package com.example.securityservice.Srvice;

import com.example.securityservice.DTO.TokenDto;

import java.util.Map;

public interface TokenService {
    public Map<String,String> jwToken(TokenDto tokenDto);
}
