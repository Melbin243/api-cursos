package com.api_cursos.controller;

import com.api_cursos.dto.course.*;
import com.api_cursos.service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAll() {
        List<CourseResponse> cursos = courseService.getAll();

        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDetailResponse> getById(@PathVariable Long id) {
        CourseDetailResponse course = courseService.getById(id);

        return ResponseEntity.ok(course);
    }

    @PostMapping
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseRequest courseRequest, HttpServletRequest req) {

        CourseResponse cursoCreado = courseService.create(courseRequest);

        String baseUrl = req.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + cursoCreado.id());

        return ResponseEntity.created(newLocation).body(cursoCreado);
    }

    @PostMapping("/enroll")
    public ResponseEntity<EnrollmentResponse> enrollStudent(@Valid @RequestBody EnrollmentRequest request) {
        EnrollmentResponse enrolledStudent = courseService.enrollStudent(request);

        return ResponseEntity.ok(enrolledStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> update(@PathVariable Long id, @Valid @RequestBody CourseRequest request) {
        CourseResponse updatedCourse = courseService.update(id, request);

        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);

        return ResponseEntity.noContent().build();
    }



}
