package com.prjt.noteservice.DTO;

import com.prjt.noteservice.Entities.Examen;
import com.prjt.noteservice.Entities.NoteKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequesteNoteDTO {
    private NoteKey id ;
    private double note;
    private String mention;
    private Examen examen;
}
