package com.api_cursos.dto.curso;

public record InscripcionResponse(
        Long id,
        String nombre,
        String descripcion,
        String profesor,
        EstudianteDetalle estudiante
) {
}
