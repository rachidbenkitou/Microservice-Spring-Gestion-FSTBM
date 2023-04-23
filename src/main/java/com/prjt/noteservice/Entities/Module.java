package com.prjt.noteservice.Entities;

import com.prjt.noteservice.Enums.Semestre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Module implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int moduleId;
    private String moduleName;
    @Enumerated(EnumType.STRING)
    private Semestre semestre;
    @OneToMany(mappedBy = "module")
    private List<Examen> examen;
}
