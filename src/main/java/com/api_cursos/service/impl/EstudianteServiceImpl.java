package com.api_cursos.service.impl;

import com.api_cursos.dto.estudiante.EstudianteRequest;
import com.api_cursos.dto.estudiante.EstudianteResponse;
import com.api_cursos.service.EstudianteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService {
    @Override
    public List<EstudianteResponse> getAll() {
        return List.of();
    }

    @Override
    public EstudianteResponse getOne(Long id) {
        return null;
    }

    @Override
    public EstudianteResponse create(EstudianteRequest estudiante) {
        return null;
    }

    @Override
    public EstudianteResponse update(Long id, EstudianteRequest estudiante) {
        return null;
    }

    @Override
    public Void delete(Long id) {
        return null;
    }
}
