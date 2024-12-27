package com.example.ToDo.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserCreateRequest(
        @Size(max = 50, message = "Title must not exceed 50 characters")
        String userName,
        @Size(max = 255, message = "Content must not exceed 255 characters")
        String password,
        boolean enabled)

{
}
