package com.api_cursos.dto.profesor;

import java.util.List;

public record ProfesorResponse(
        Long id,
        String dni,
        String nombre,
        String apellido,
        String email,
        String profesion,
        String telefono
) {
}
