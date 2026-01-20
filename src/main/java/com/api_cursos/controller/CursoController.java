package com.api_cursos.controller;

import com.api_cursos.dto.curso.*;
import com.api_cursos.service.CursoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoResponse>> getAll() {
        List<CursoResponse> cursos = cursoService.getAll();

        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDetalleResponse> getOne(@PathVariable Long id) {
        CursoDetalleResponse curso = cursoService.getOne(id);

        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<CursoResponse> create(@RequestBody CursoRequest requestDto, HttpServletRequest req) {

        CursoResponse cursoCreado = cursoService.create(requestDto);

        String baseUrl = req.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + cursoCreado.id());

        return ResponseEntity.created(newLocation).body(cursoCreado);
    }

    @PostMapping("/inscripciones")
    public ResponseEntity<InscripcionResponse> asociarEstudiante(@RequestBody InscripcionRequest request) {
        InscripcionResponse estudianteInscrito = cursoService.asociarEstudiante(request);

        return ResponseEntity.ok(estudianteInscrito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> update(@PathVariable Long id, @RequestBody CursoRequest request) {
        CursoResponse cursoActualizado = cursoService.update(id, request);

        return ResponseEntity.ok(cursoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cursoService.delete(id);

        return ResponseEntity.noContent().build();
    }



}
