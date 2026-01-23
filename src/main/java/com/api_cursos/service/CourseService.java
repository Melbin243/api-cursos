package com.api_cursos.service;

import com.api_cursos.dto.course.*;

import java.util.List;

public interface CourseService {

    List<CourseResponse> getAll();
    CourseDetailResponse getById(Long id);
    CourseResponse create(CourseRequest request);
    EnrollmentResponse enrollStudent(EnrollmentRequest request);
    CourseResponse update(Long id, CourseRequest request);
    void delete(Long id);


}
