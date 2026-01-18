package com.api_cursos.mapper;

import com.api_cursos.dto.profesor.ProfesorDetalleResponse;
import com.api_cursos.dto.profesor.ProfesorRequest;
import com.api_cursos.dto.profesor.ProfesorResponse;
import com.api_cursos.persistence.entity.Curso;
import com.api_cursos.persistence.entity.Profesor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfesorMapper {

    public ProfesorResponse toResponse(Profesor profesor) {
        if (profesor == null) return null;

        return new ProfesorResponse(
                profesor.getId(),
                profesor.getDni(),
                profesor.getNombre(),
                profesor.getApellido(),
                profesor.getEmail(),
                profesor.getProfesion(),
                profesor.getTelefono()
        );
    }

    public ProfesorDetalleResponse toDetalle(Profesor profesor) {
        if (profesor == null) return null;

        return new ProfesorDetalleResponse(
                profesor.getId(),
                profesor.getDni(),
                profesor.getNombre(),
                profesor.getApellido(),
                profesor.getEmail(),
                profesor.getProfesion(),
                profesor.getTelefono(),
                profesor.getCursos().stream()
                        .map(Curso::getNombre)
                        .toList()
        );
    }

    public Profesor toEntity(ProfesorRequest request) {
        if (request == null) return null;

        Profesor profesor = new Profesor();
        profesor.setDni(request.dni());
        profesor.setNombre(request.nombre());
        profesor.setApellido(request.apellido());
        profesor.setEmail(request.email());
        profesor.setProfesion(request.profesion());
        profesor.setTelefono(request.telefono());

        return profesor;
    }

    public void updateEntity(ProfesorRequest request, Profesor profesor) {
        if (request == null || profesor == null) return;

        if (request.dni() != null) {
            profesor.setDni(request.dni());
        }
        if (request.nombre() != null) {
            profesor.setNombre(request.nombre());
        }
        if (request.apellido() != null) {
            profesor.setApellido(request.apellido());

        }
        if (request.email() != null) {
            profesor.setEmail(request.email());

        }
        if (request.profesion() != null) {
            profesor.setProfesion(request.profesion());

        }
        if (request.telefono() != null) {
            profesor.setTelefono(request.telefono());

        }

    }

}
