package com.api_cursos.dto.curso;

public record CursoRequest(
        String nombre,
        String descripcion,
        Long idProfesor

) {
}
