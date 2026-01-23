package com.api_cursos.dto.course;

public record CourseResponse(
        Long id,
        String name,
        String description,
        String teacher
) {
}
