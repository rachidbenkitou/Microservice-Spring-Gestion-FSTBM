package com.example.note2service.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private TypeExamen type;
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;
    private Date dateExam;
    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

}
