package com.api_cursos.dto.curso;

public record CursoResponse(
        Long id,
        String nombre,
        String descripcion,
        String profesor
) {
}
