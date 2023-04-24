package com.weCouldCode.fstBmManagement.noteService.Mappers;

import com.weCouldCode.fstBmManagement.noteService.DTO.RequesteNoteDTO;
import com.weCouldCode.fstBmManagement.noteService.DTO.ResponseNoteDTO;
import com.weCouldCode.fstBmManagement.noteService.Entities.Note;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface NoteMapper {
    Note dtoToModel(RequesteNoteDTO requesteNoteDTO);

    ResponseNoteDTO modelToDto(Note note);

    List< ResponseNoteDTO > modelToDtos(List<Note> noteList);
}
