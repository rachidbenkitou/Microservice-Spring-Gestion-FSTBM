package com.example.note2service.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int moduleId;
    @Column(unique = true)
    private String moduleName;
    @Enumerated(EnumType.STRING)
    private Semestre moduleSemestre;
    @OneToMany(mappedBy = "module" , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Examen> examens;

}
