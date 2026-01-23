package com.api_cursos.dto.course;

public record CourseRequest(
        String name,
        String description,
        Long teacherId

) {
}
