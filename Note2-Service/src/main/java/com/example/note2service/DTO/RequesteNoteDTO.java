package com.example.note2service.DTO;

import com.example.note2service.Entities.Etudiant;
import com.example.note2service.Entities.Examen;
import com.example.note2service.Entities.NoteKey;
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
    private double oldNote;
    private long etudiantApogee;
}
