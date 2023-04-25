package com.example.note2service.DTO;

import com.example.note2service.Entities.Semestre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseModuleDTO {
    private int modeuleId;
    private String moduleName;
    private Semestre moduleSemestre;
}
