package com.api_cursos.dto.student;

import java.util.List;

public record StudentRequest(
        String name,
        String lastName,
        String email,
        List<Long> courseIds
) {
}
