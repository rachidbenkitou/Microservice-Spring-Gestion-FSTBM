package com.example.note2service.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Examen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private TypeExamen type;
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;
    private Date dateExam;
   @OneToMany(mappedBy = "examen", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   @JsonIgnore
   private List<Note> noteList;
    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

}
