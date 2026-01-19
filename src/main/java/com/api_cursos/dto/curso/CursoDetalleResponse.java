package com.api_cursos.dto.curso;

import java.util.List;

public record CursoDetalleResponse(
        Long id,
        String nombre,
        String descripcion,
        String profesor,
        List<EstudianteDetalle> estudiantes
) {
}
