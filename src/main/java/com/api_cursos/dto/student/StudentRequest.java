package com.api_cursos.dto.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record StudentRequest(
        @NotBlank String name,
        @NotBlank String lastName,
        @Email String email,
        List<Long> courseIds
) {
}
