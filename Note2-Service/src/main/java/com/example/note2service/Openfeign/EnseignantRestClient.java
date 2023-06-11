package com.example.note2service.Openfeign;

import com.example.note2service.Entities.Enseignant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "enseigant-service" , url = "http://localhost:8222")

public interface EnseignantRestClient {
    @GetMapping(path = "api/v1/enseignant/cin/{cin}")
    Enseignant findEnseignantByCin(@PathVariable String cin);
}
