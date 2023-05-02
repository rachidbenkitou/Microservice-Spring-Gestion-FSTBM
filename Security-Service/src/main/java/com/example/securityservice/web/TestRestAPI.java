package com.example.securityservice.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestRestAPI {
    @GetMapping("/datatest")
   @PreAuthorize("hasAuthority('SCOPE_USER')")
    public Map<String,Object>dataTest(){
        return Map.of("message","Data test");
    }
    @GetMapping ("/savedata")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Map<String,Object>saveData(){
        return Map.of("dataSaved","ysf");
    }
}
