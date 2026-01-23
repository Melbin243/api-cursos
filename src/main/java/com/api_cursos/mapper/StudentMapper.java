package com.api_cursos.mapper;

import com.api_cursos.dto.student.StudentDetailResponse;
import com.api_cursos.dto.student.StudentRequest;
import com.api_cursos.dto.student.StudentResponse;
import com.api_cursos.persistence.entity.Course;
import com.api_cursos.persistence.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    public StudentResponse toResponse(Student student) {
        if (student == null) return null;

        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getLastName(),
                student.getEmail()
        );
    }

    public StudentDetailResponse toDetailResponse(Student student) {
        if (student == null) return null;

        return new StudentDetailResponse(
                student.getId(),
                student.getName(),
                student.getLastName(),
                student.getEmail(),
                student.getCourses().stream()
                        .map(Course::getName)
                        .collect(Collectors.toList())
        );
    }

    public Student toEntity(StudentRequest request) {
        if (request == null) return null;

        return Student.builder()
                .name(request.name().trim())
                .lastName(request.lastName().trim())
                .email(request.email().trim())
                .courses(new ArrayList<>())
                .build();
    }

    public void updateEntity(StudentRequest request, Student student) {
        if (request == null || student == null ) return;

        student.setName(request.name());
        student.setLastName(request.lastName());
        student.setEmail(request.email());
    }

}
