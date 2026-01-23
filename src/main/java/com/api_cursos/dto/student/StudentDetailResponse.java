package com.api_cursos.dto.student;

import java.util.List;

public record StudentDetailResponse(
        Long id,
        String name,
        String lastName,
        String email,
        List<String> courses
) {
}
