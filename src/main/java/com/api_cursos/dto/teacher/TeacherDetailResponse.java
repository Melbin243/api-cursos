package com.api_cursos.dto.teacher;

import java.util.List;

public record TeacherDetailResponse(
        Long id,
        String dni,
        String name,
        String lastName,
        String email,
        String specialization,
        String phone,
        List<String> courses

) {
}
