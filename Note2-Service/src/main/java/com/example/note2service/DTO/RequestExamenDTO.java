package com.example.note2service.DTO;

import com.example.note2service.Entities.Module;
import com.example.note2service.Entities.TypeExamen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestExamenDTO implements Serializable {
    private long id;
    private TypeExamen type;
    Date creationDateTime;
    private Date dateExam;
    private Module module;
}
