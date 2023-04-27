package com.example.note2service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseExamenDTO {
    private long id;
    private String type;
    Date creationDateTime;
    private Date dateExam;
    private  Module module;

}
