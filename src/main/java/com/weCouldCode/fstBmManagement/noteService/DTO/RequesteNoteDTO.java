package com.weCouldCode.fstBmManagement.noteService.DTO;

import com.weCouldCode.fstBmManagement.noteService.Entities.Examen;
import com.weCouldCode.fstBmManagement.noteService.Entities.NoteKey;
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
