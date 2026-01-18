package com.api_cursos.service.impl;

import com.api_cursos.dto.profesor.ProfesorDetalleResponse;
import com.api_cursos.dto.profesor.ProfesorRequest;
import com.api_cursos.dto.profesor.ProfesorResponse;
import com.api_cursos.mapper.ProfesorMapper;
import com.api_cursos.persistence.entity.Curso;
import com.api_cursos.persistence.entity.Profesor;
import com.api_cursos.persistence.repository.CursoRepository;
import com.api_cursos.persistence.repository.ProfesorRepository;
import com.api_cursos.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final ProfesorMapper profesorMapper;
    private final CursoRepository cursoRepository;


    @Override
    public List<ProfesorResponse> getAll() {
        List<Profesor> profesores = profesorRepository.findAll();

        return profesores.stream()
                .map(profesorMapper::toResponse)
                .toList();
    }

    @Override
    public ProfesorDetalleResponse getOne(Long id) {
        Profesor profesor = profesorRepository.findWithCursos(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        return profesorMapper.toDetalle(profesor);
    }

    @Override
    @Transactional
    public ProfesorResponse create(ProfesorRequest request) {
        Profesor profesor = profesorMapper.toEntity(request);

        return profesorMapper.toResponse(profesorRepository.save(profesor));
    }

    @Override
    @Transactional
    public ProfesorResponse update(Long id, ProfesorRequest request) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        profesorMapper.updateEntity(request, profesor);

        if (request.idCursos() != null) {

            // Limpiar los cursos antiguos (desvincular)
            profesor.getCursos().forEach(curso -> curso.setProfesor(null));

            List<Curso> nuevoCursos = cursoRepository.findAllById(request.idCursos());

            if (nuevoCursos.size() != request.idCursos().size()) {
                throw new RuntimeException("Uno o más IDs de cursos son inválidos");
            }

            nuevoCursos.forEach(curso -> {
                if (curso.getProfesor() != null && !curso.getProfesor().getId().equals(id)) {

                    throw new RuntimeException("El curso " + curso.getNombre() + " ya tiene un profesor asignado");
                }
                curso.setProfesor(profesor);
            });

            profesor.setCursos(nuevoCursos);
        }

        return profesorMapper.toResponse(profesorRepository.save(profesor));
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        // Desvincular profesor de los cursos
        profesor.getCursos().forEach(curso -> curso.setProfesor(null));

        profesorRepository.delete(profesor);

    }
}
