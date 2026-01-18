package com.api_cursos.controller;

import com.api_cursos.dto.estudiante.EstudianteDetalleResponse;
import com.api_cursos.dto.estudiante.EstudianteRequest;
import com.api_cursos.dto.estudiante.EstudianteResponse;
import com.api_cursos.persistence.entity.Estudiante;
import com.api_cursos.service.EstudianteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteResponse>> getAll() {
        return ResponseEntity.ok(
                estudianteService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDetalleResponse> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(
                estudianteService.getOne(id)
        );
    }

    @PostMapping
    public ResponseEntity<EstudianteResponse> create(@RequestBody EstudianteRequest estudiante,
                                                     HttpServletRequest request) {
        EstudianteResponse nuevoEstudiante = estudianteService.create(estudiante);

        String baseUrl = request.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + nuevoEstudiante.id());

        return ResponseEntity.created(newLocation).body(nuevoEstudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteResponse> update(@PathVariable Long id,
                                                     @RequestBody EstudianteRequest request){
        EstudianteResponse estudianteActualizado = estudianteService.update(id, request);
        return ResponseEntity.ok(estudianteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estudianteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
