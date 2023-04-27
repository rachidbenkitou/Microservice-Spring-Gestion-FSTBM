package com.example.note2service.DTO;

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
    private int modeuleId;
    private String moduleName;
    private Semestre moduleSemestre;
}
