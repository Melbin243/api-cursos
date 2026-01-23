package com.api_cursos.dto.course;

public record EnrollmentResponse(
        Long id,
        String name,
        String description,
        String teacher,
        StudentSimpleResponse student
) {
}
