package com.prjt.noteservice.Mappers;

import com.prjt.noteservice.DTO.RequestExamenDTO;
import com.prjt.noteservice.DTO.RequesteNoteDTO;
import com.prjt.noteservice.DTO.ResponseExamenDTO;
import com.prjt.noteservice.DTO.ResponseNoteDTO;
import com.prjt.noteservice.Entities.Examen;
import com.prjt.noteservice.Entities.Note;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface NoteMapper {
    Note dtoToModel(RequesteNoteDTO requesteNoteDTO);

    ResponseNoteDTO modelToDto(Note note);

    List< ResponseNoteDTO > modelToDtos(List<Note> noteList);
}
