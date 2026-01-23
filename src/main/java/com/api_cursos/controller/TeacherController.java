package com.api_cursos.controller;

import com.api_cursos.dto.teacher.TeacherDetailResponse;
import com.api_cursos.dto.teacher.TeacherRequest;
import com.api_cursos.dto.teacher.TeacherResponse;
import com.api_cursos.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAll() {
        List<TeacherResponse> teachers = teacherService.getAll();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDetailResponse> getById(@PathVariable Long id) {
        TeacherDetailResponse teacher = teacherService.getById(id);

        return  ResponseEntity.ok(teacher);
    }

    @PostMapping
    public ResponseEntity<TeacherResponse> create(@RequestBody TeacherRequest teacherRequest,
                                                  HttpServletRequest req) {
        TeacherResponse teacher = teacherService.create(teacherRequest);

        String baseUrl = req.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + teacher.id());


        return ResponseEntity.created(newLocation).body(teacher);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> update(@PathVariable Long id,
                                                  @RequestBody TeacherRequest request) {
        TeacherResponse updatedTeacher = teacherService.update(id, request);

        return ResponseEntity.ok(updatedTeacher);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);

        return ResponseEntity.noContent().build();
    }



}
