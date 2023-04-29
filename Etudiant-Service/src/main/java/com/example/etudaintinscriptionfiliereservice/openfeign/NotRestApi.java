package com.example.etudaintinscriptionfiliereservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "NOTE-SERVICE")
public interface NotRestApi {
  /*  @GetMapping(path = "/note-service/find/{idFiliere}")
    List<Module> getModules(@PathVariable(name = "idFiliere") String idFiliere);*/
/*    @GetMapping(path = "/note-service/find/{idEtudiant}")
    List<Note> getNotes(@PathVariable(name = "idEtudiant") String idEtudiant);*/
}
