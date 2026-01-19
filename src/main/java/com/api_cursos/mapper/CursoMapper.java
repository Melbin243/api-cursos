package com.api_cursos.mapper;

import com.api_cursos.dto.curso.CursoDetalleResponse;
import com.api_cursos.dto.curso.CursoRequest;
import com.api_cursos.dto.curso.CursoResponse;
import com.api_cursos.dto.curso.EstudianteDetalle;
import com.api_cursos.persistence.entity.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoResponse toResponse(Curso curso) {
        if (curso == null) return null;

        return new CursoResponse(
                curso.getId(),
                curso.getNombre(),
                curso.getDescripcion(),
                curso.getProfesor().getNombre() + " " + curso.getProfesor().getApellido()
        );
    }

    public CursoDetalleResponse toDetalle(Curso curso) {
        if (curso == null) return null;

        return new CursoDetalleResponse(
                curso.getId(),
                curso.getNombre(),
                curso.getDescripcion(),
                curso.getProfesor().getNombre() + " " + curso.getProfesor().getApellido(),
                curso.getEstudiantes().stream()
                        .map(e -> new EstudianteDetalle(
                                e.getId(), e.getNombre(), e.getApellido()
                        )).toList()
        );
    }

    public Curso toEntity(CursoRequest request) {
        if (request == null) return null;

        Curso curso = new Curso();
        curso.setNombre(request.nombre());
        curso.setDescripcion(request.descripcion());

        return curso;
    }

}
