package com.api_cursos.controller;

import com.api_cursos.dto.course.*;
import com.api_cursos.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Courses", description = "Controller para los cursos")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    @Operation(
            summary = "Listar Cursos",
            description = "Método para consultar todos los cursos"
    )
    public ResponseEntity<List<CourseResponse>> getAll() {
        List<CourseResponse> cursos = courseService.getAll();

        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Listar un Curso",
            description = "Método para traer un curso mas el maestro encargado y su lista de alumnos"
    )
    public ResponseEntity<CourseDetailResponse> getById(@PathVariable Long id) {
        CourseDetailResponse course = courseService.getById(id);

        return ResponseEntity.ok(course);
    }

    @PostMapping
    @Operation(
            summary = "Crear un Curso",
            description = "Método para crear un curso",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Recibe el nombre, la description y el maestro encargado"
            )
    )
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseRequest courseRequest, HttpServletRequest req) {

        CourseResponse cursoCreado = courseService.create(courseRequest);

        String baseUrl = req.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + cursoCreado.id());

        return ResponseEntity.created(newLocation).body(cursoCreado);
    }

    @PostMapping("/enroll")
    @Operation(
            summary = "Inscribir un estudiante",
            description = "Método para asociar un estudiante a un curso",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Recibe el id del estudiante y el id del curso",
                    required = true
            )
    )
    public ResponseEntity<EnrollmentResponse> enrollStudent(@Valid @RequestBody EnrollmentRequest request) {
        EnrollmentResponse enrolledStudent = courseService.enrollStudent(request);

        return ResponseEntity.ok(enrolledStudent);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un Curso",
            description = "Método para actualizar un curso",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Recibe el nombre, la description y el maestro encargado"
            )
    )
    public ResponseEntity<CourseResponse> update(@PathVariable Long id, @Valid @RequestBody CourseRequest request) {
        CourseResponse updatedCourse = courseService.update(id, request);

        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un Curso",
            description = "Elimina un curso mediante el id"
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);

        return ResponseEntity.noContent().build();
    }



}
