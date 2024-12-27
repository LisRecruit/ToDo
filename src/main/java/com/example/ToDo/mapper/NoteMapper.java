package com.example.ToDo.mapper;


import com.example.ToDo.model.Note;
import com.example.ToDo.model.dto.NoteCreateRequest;
import com.example.ToDo.model.dto.NoteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NoteMapper {
    NoteResponse toNoteResponse (Note note);
    @Mapping(target = "id", ignore = true)
    Note toNote(NoteCreateRequest createRequest);
}
