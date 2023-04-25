package com.example.note2service.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private int modeuleId;
    @Column(unique = true)
    private String moduleName;
    @Enumerated(EnumType.STRING)
    private Semestre moduleSemestre;
    @OneToMany(mappedBy = "module")
    private List<Examen> examens;

}
