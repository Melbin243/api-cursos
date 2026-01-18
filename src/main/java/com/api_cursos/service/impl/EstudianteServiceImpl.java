package com.api_cursos.service.impl;

import com.api_cursos.dto.estudiante.EstudianteDetalleResponse;
import com.api_cursos.dto.estudiante.EstudianteRequest;
import com.api_cursos.dto.estudiante.EstudianteResponse;
import com.api_cursos.mapper.EstudianteMapper;
import com.api_cursos.persistence.entity.Curso;
import com.api_cursos.persistence.entity.Estudiante;
import com.api_cursos.persistence.repository.CursoRepository;
import com.api_cursos.persistence.repository.EstudianteRepository;
import com.api_cursos.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;
    private final CursoRepository cursoRepository;

    @Override
    public List<EstudianteResponse> getAll() {
        List<Estudiante> estudiante = estudianteRepository.findAll();

        return estudiante.stream()
                .map(estudianteMapper::toResponse).toList();
    }

    @Override
    public EstudianteDetalleResponse getOne(Long id) {
        Estudiante estudiante = estudianteRepository.findOneWithCursos(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        return estudianteMapper.toDetalle(estudiante);
    }

    @Override
    @Transactional
    public EstudianteResponse create(EstudianteRequest estudiante) {
        Estudiante nuevoEstudiante = estudianteMapper.toEntity(estudiante);

        if (estudiante.cursosIds() != null ) {
            for (Long id : estudiante.cursosIds()) {
                Curso curso = cursoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Curso " + id + " no encontrado"));

                nuevoEstudiante.getCursos().add(curso);
            }
        }

        return estudianteMapper.toResponse(
                estudianteRepository.save(nuevoEstudiante)
        );
    }

    @Override
    @Transactional
    public EstudianteResponse update(Long id, EstudianteRequest request) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        estudianteMapper.updateEntity(request, estudiante);

        if (request.cursosIds() != null) {
            List<Curso> nuevosCursos = cursoRepository.findAllById(request.cursosIds());
            estudiante.setCursos(nuevosCursos);
        }

        return estudianteMapper.toResponse(estudianteRepository.save(estudiante));
    }

    @Override
    public void delete(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante " + id + " no encontrado"));

        estudianteRepository.delete(estudiante);
    }
}
