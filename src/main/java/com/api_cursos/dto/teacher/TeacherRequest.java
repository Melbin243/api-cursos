package com.api_cursos.dto.teacher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record TeacherRequest(
        @NotBlank
        String dni,

        @NotBlank
        String name,

        @NotBlank
        String lastName,

        @Email
        String email,

        @NotBlank
        String specialization,

        @NotBlank
        @Pattern(regexp = "\\d{7,15}", message = "El teléfono debe tener entre 7 y 15 dígitos")
        String phone
) {
}
