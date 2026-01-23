package com.api_cursos.dto.teacher;

import java.util.List;

public record TeacherRequest(
        String dni,
        String name,
        String lastName,
        String email,
        String specialization,
        String phone,
        List<Long> courseIds
) {
}
