package com.api_cursos.service;

import com.api_cursos.dto.teacher.TeacherDetailResponse;
import com.api_cursos.dto.teacher.TeacherRequest;
import com.api_cursos.dto.teacher.TeacherResponse;

import java.util.List;

public interface TeacherService {
    List<TeacherResponse> getAll();
    TeacherDetailResponse getById(Long id);
    TeacherResponse create(TeacherRequest request);
    TeacherResponse update(Long id, TeacherRequest request);
    void delete(Long id);
}
