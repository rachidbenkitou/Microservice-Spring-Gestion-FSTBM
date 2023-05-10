package com.example.note2service.Openfeign;

import com.example.note2service.Entities.Etudiant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ETUDAINTINSCRIPTIONFILIERE-SERVICE" , url = "http://localhost:1010 ")
public interface EtudiantRestClient {
    @GetMapping(path = "/Etudiants/id/{id}")
    Etudiant findEtudiantById(@PathVariable String id);
    @GetMapping(path = "/Etudiants/apogee/{apogee}")
    Etudiant findEtudiantByApogee(@PathVariable Long apogee);
    @GetMapping(path = "/Etudiants")
    List<Etudiant>  getAll();

}

