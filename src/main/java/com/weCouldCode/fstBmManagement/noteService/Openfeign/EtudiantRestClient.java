package com.weCouldCode.fstBmManagement.noteService.Openfeign;

import com.weCouldCode.fstBmManagement.noteService.Entities.Etudiant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "application.properties" , url = "http://localhost:8082/api")
public interface EtudiantRestClient {
    @GetMapping(path = "/api/etudiants/{id}")
    Etudiant getEtudiant(@PathVariable(name = "id") long id);
    @GetMapping(path = "/api/etudiants")
    List<Etudiant> allEtudiants();
}
