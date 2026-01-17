package com.api_cursos.service;

import com.api_cursos.dto.estudiante.EstudianteDetalleResponse;
import com.api_cursos.dto.estudiante.EstudianteRequest;
import com.api_cursos.dto.estudiante.EstudianteResponse;

import java.util.List;

public interface EstudianteService {
    List<EstudianteResponse> getAll();
    EstudianteDetalleResponse getOne(Long id);
    EstudianteResponse create(EstudianteRequest estudiante);
    EstudianteResponse update(Long id, EstudianteRequest estudiante);
    void delete(Long id);

}
