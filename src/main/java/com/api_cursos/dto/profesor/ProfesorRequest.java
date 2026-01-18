package com.api_cursos.dto.profesor;

import java.util.List;

public record ProfesorRequest(
        String dni,
        String nombre,
        String apellido,
        String email,
        String profesion,
        String telefono,
        List<Long> idCursos
) {
}
