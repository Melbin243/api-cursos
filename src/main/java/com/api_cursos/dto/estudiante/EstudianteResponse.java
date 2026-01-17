package com.api_cursos.dto.estudiante;

import java.util.List;

public record EstudianteResponse(
        Long id,
        String nombre,
        String apellido,
        String email,
        List<String> cursos
) {
}
