package com.api_cursos.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequest(
        @NotBlank(message = "El nombre no debe estar vacío")
        String name,
        String description,
        @NotNull()
        Long teacherId

) {
}
