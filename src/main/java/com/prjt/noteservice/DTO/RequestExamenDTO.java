package com.prjt.noteservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestExamenDTO {
    private long id;
    private String type;
    Date creationDateTime;
    private Date dateExam;
}
