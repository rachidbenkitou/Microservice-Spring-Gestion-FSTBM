package com.example.note2service.DTO;

import com.example.note2service.Entities.Module;
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
public class ResponseExamenDTO implements Serializable {
    private long id;
    private String type;
    Date creationDateTime;
    private Date dateExam;
    private Module module;
}
