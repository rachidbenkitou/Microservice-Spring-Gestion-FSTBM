package com.example.note2service.Openfeign;

import com.example.note2service.Entities.Etudiant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ETUDAINTINSCRIPTIONFILIERE-SERVICE")
public interface EtudiantRestClient {
    @GetMapping(path = "/Etudiants/id/{id}")
    Etudiant findEtudiantById(@PathVariable String id);
    @GetMapping(path = "/Etudiants/apogee/{apogee}")
    Etudiant findEtudiantByApogee(@PathVariable Long apogee);
    @GetMapping(path = "/Etudiants")
    List<Etudiant>  getAll();

}

