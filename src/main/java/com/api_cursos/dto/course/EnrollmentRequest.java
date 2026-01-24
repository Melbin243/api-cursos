package com.api_cursos.dto.course;

import jakarta.validation.constraints.NotNull;

public record EnrollmentRequest(
        @NotNull
        Long courseId,
        @NotNull
        Long studentId
) {
}
