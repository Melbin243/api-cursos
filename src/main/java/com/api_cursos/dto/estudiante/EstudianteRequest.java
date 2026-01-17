package com.api_cursos.dto.estudiante;

public record EstudianteRequest(
        String nombre,
        String apellido,
        String email,
        String cursos
) {
}
