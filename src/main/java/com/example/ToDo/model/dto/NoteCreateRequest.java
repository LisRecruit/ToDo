package com.example.ToDo.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder

public record NoteCreateRequest(
                                 @Size(max = 100, message = "Title must not exceed 100 characters")
                                 String title,
                                 @Size(max = 255, message = "Content must not exceed 255 characters")
                                 String content) {
    public String getTitle() {
        return title;
    }
    public String getContent(){
        return content;
    }
}
