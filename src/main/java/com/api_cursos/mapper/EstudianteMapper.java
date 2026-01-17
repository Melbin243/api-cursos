package com.api_cursos.mapper;

import com.api_cursos.dto.estudiante.EstudianteDetalleResponse;
import com.api_cursos.dto.estudiante.EstudianteRequest;
import com.api_cursos.dto.estudiante.EstudianteResponse;
import com.api_cursos.persistence.entity.Curso;
import com.api_cursos.persistence.entity.Estudiante;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class EstudianteMapper {

    public EstudianteResponse toResponse(Estudiante estudiante) {
        if (estudiante == null) return null;

        return new EstudianteResponse(
                estudiante.getId(),
                estudiante.getNombre(),
                estudiante.getApellido(),
                estudiante.getEmail()
        );
    }

    public EstudianteDetalleResponse toDetalle(Estudiante estudiante) {
        if (estudiante == null) return null;

        return new EstudianteDetalleResponse(
                estudiante.getId(),
                estudiante.getNombre(),
                estudiante.getApellido(),
                estudiante.getEmail(),
                estudiante.getCursos().stream()
                        .map(Curso::getNombre)
                        .collect(Collectors.toList())
        );
    }

    public Estudiante toEntity(EstudianteRequest request) {
        if (request == null) return null;

        return Estudiante.builder()
                .nombre(request.nombre().trim())
                .apellido(request.apellido().trim())
                .email(request.email().trim())
                .cursos(new ArrayList<>())
                .build();
    }

    public void updateEntity(EstudianteRequest request, Estudiante estudiante) {
        if (request == null || estudiante == null ) return;

        estudiante.setNombre(request.nombre().trim());
        estudiante.setApellido(request.apellido().trim());
        estudiante.setEmail(request.email().trim());
    }

}
