package com.api_cursos.dto.course;

public record EnrollmentRequest(
        Long courseId,
        Long studentId
) {
}
