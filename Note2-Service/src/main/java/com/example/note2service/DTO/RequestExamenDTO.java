package com.example.note2service.DTO;

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
    private String type;
    Date creationDateTime;
    private Date dateExam;
}
