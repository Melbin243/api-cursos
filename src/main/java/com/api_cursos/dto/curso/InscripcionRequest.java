package com.api_cursos.dto.curso;

public record InscripcionRequest(
        Long idCurso,
        Long idEstudiante
) {
}
