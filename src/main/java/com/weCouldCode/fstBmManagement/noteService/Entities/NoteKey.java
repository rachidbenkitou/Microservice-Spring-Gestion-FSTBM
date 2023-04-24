package com.weCouldCode.fstBmManagement.noteService.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteKey  implements Serializable {
    private long etudiantId;
    @Column(name = "examen_id")
    private long examenId;
}
