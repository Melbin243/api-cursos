package com.api_cursos.dto.teacher;

public record TeacherResponse(
        Long id,
        String dni,
        String name,
        String lastName,
        String email,
        String specialization,
        String phone
) {
}
