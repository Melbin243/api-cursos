package com.api_cursos.mapper;

import com.api_cursos.dto.curso.*;
import com.api_cursos.persistence.entity.Curso;
import com.api_cursos.persistence.entity.Estudiante;
import com.api_cursos.persistence.entity.Profesor;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoResponse toResponse(Curso curso) {
        if (curso == null) return null;

        String nombreProfesor = (curso.getProfesor() != null)
                ? curso.getProfesor().getNombre() + " " + curso.getProfesor().getApellido()
                : "Sin profesor asignado";

        return new CursoResponse(
                curso.getId(),
                curso.getNombre(),
                curso.getDescripcion(),
                nombreProfesor
        );
    }

    public CursoDetalleResponse toCursoDetalleResponse(Curso curso) {
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

    public Curso toEntity(CursoRequest request, Profesor profesor) {
        if (request == null) return null;

        Curso curso = new Curso();
        curso.setNombre(request.nombre());
        curso.setDescripcion(request.descripcion());
        curso.setProfesor(profesor);

        return curso;
    }

    public InscripcionResponse toInscripcionResponse(Curso curso, Estudiante estudiante) {
        if (curso == null) return null;

        String nombreProfesor = (curso.getProfesor() != null)
                ? curso.getProfesor().getNombre() + " " + curso.getProfesor().getApellido()
                : "Sin profesor asignado";

        return new InscripcionResponse(
                curso.getId(),
                curso.getNombre(),
                curso.getDescripcion(),
                nombreProfesor,
                new EstudianteDetalle(
                        estudiante.getId(),
                        estudiante.getNombre(),
                        estudiante.getApellido()
                )
        );
    }

    public void updateEntity(Curso cursoViejo, CursoRequest cursoNuevo, Profesor profesor) {
        if (cursoViejo == null || cursoNuevo == null) return;

        cursoViejo.setNombre(cursoNuevo.nombre());
        cursoViejo.setDescripcion(cursoNuevo.descripcion());
        cursoViejo.setProfesor(profesor);
    }

}
