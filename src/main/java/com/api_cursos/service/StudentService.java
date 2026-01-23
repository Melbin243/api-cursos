package com.api_cursos.service;

import com.api_cursos.dto.student.StudentDetailResponse;
import com.api_cursos.dto.student.StudentRequest;
import com.api_cursos.dto.student.StudentResponse;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAll();
    StudentDetailResponse getById(Long id);
    StudentResponse create(StudentRequest estudiante);
    StudentResponse update(Long id, StudentRequest estudiante);
    void delete(Long id);

}
