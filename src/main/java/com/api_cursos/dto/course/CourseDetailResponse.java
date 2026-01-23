package com.api_cursos.dto.course;

import java.util.List;

public record CourseDetailResponse(
        Long id,
        String name,
        String description,
        String teacher,
        List<StudentSimpleResponse> students
) {
}
