package com.api_cursos.controller;

import com.api_cursos.dto.profesor.ProfesorDetalleResponse;
import com.api_cursos.dto.profesor.ProfesorRequest;
import com.api_cursos.dto.profesor.ProfesorResponse;
import com.api_cursos.service.ProfesorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<ProfesorResponse>> getAll() {
        List<ProfesorResponse> profesores = profesorService.getAll();
        return ResponseEntity.ok(profesores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDetalleResponse> getOne(@PathVariable Long id) {
        ProfesorDetalleResponse profesor = profesorService.getOne(id);

        return  ResponseEntity.ok(profesor);
    }

    @PostMapping
    public ResponseEntity<ProfesorResponse> create(@RequestBody ProfesorRequest requestDto, HttpServletRequest req) {
        ProfesorResponse profesor = profesorService.create(requestDto);

        String baseUrl = req.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + profesor.id());


        return ResponseEntity.created(newLocation).body(profesor);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorResponse> update(@PathVariable Long id, @RequestBody ProfesorRequest request) {
        ProfesorResponse profesor = profesorService.update(id, request);

        return ResponseEntity.ok(profesor);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        profesorService.delete(id);

        return ResponseEntity.noContent().build();
    }



}
