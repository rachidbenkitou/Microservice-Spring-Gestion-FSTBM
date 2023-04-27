package com.example.note2service.Mappers;

import com.example.note2service.DTO.RequesteNoteDTO;
import com.example.note2service.DTO.ResponseNoteDTO;
import com.example.note2service.Entities.Note;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Service
public interface NoteMapper {
    Note dtoToModel(RequesteNoteDTO requesteNoteDTO);

    ResponseNoteDTO modelToDto(Note note);

    List< ResponseNoteDTO > modelToDtos(List<Note> noteList);
    RequesteNoteDTO modelToRequestDto(Note note);
}
