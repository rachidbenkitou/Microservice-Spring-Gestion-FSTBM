package com.example.note2service.Openfeign;

import com.example.note2service.Entities.Etudiant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ETUDIANT-SERVICE" , url = "http://localhost:8082")
public interface EtudiantRestClient {
    @GetMapping(path = "/etudiants/{id}")
    Etudiant getEtudiant(@PathVariable(name = "id") long id);
    @GetMapping(path = "/etudiants/apogee/{apogee}")
    Etudiant getEtudiantByApogee(@PathVariable(name = "apogee") long apogee);
    @GetMapping(path = "/etudiants")
    List<Etudiant> allEtudiants();
}

