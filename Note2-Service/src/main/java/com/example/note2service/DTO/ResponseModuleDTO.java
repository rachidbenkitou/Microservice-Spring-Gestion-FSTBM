package com.example.note2service.DTO;

import com.example.note2service.Entities.Enseignant;
import com.example.note2service.Entities.Module;
import com.example.note2service.Entities.Semestre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseModuleDTO implements Serializable {
    private int moduleId;
    private String moduleName;
    private Semestre moduleSemestre;
    private String idFiliere;
    private Enseignant enseignant;

}
