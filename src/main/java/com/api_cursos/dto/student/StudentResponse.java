package com.api_cursos.dto.student;

public record StudentResponse(
        Long id,
        String name,
        String lastName,
        String email
) {
}
