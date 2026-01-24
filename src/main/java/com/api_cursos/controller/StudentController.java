package com.api_cursos.controller;

import com.api_cursos.dto.student.StudentDetailResponse;
import com.api_cursos.dto.student.StudentRequest;
import com.api_cursos.dto.student.StudentResponse;
import com.api_cursos.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAll() {
        return ResponseEntity.ok(
                studentService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDetailResponse> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(
                studentService.getById(id)
        );
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid  @RequestBody StudentRequest studentRequest,
                                                  HttpServletRequest req) {
        StudentResponse newStudent = studentService.create(studentRequest);

        String baseUrl = req.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + newStudent.id());

        return ResponseEntity.created(newLocation).body(newStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody StudentRequest request){
        StudentResponse updatedStudent = studentService.update(id, request);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
