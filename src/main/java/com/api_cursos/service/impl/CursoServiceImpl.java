package com.api_cursos.service.impl;

import com.api_cursos.dto.curso.*;
import com.api_cursos.mapper.CursoMapper;
import com.api_cursos.persistence.entity.Curso;
import com.api_cursos.persistence.entity.Estudiante;
import com.api_cursos.persistence.entity.Profesor;
import com.api_cursos.persistence.repository.CursoRepository;
import com.api_cursos.persistence.repository.EstudianteRepository;
import com.api_cursos.persistence.repository.ProfesorRepository;
import com.api_cursos.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;
    private final ProfesorRepository profesorRepository;
    private final EstudianteRepository estudianteRepository;

    @Override
    public List<CursoResponse> getAll() {
        List<Curso> cursos = cursoRepository.findAllWithProfesor();

        return cursos.stream()
                .map(cursoMapper::toResponse)
                .toList();
    }

    @Override
    public CursoDetalleResponse getOne(Long id) {
        Curso curso = cursoRepository.findWithProfesorAndEstudiante(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        return cursoMapper.toCursoDetalleResponse(curso);
    }

    @Override
    @Transactional
    public CursoResponse create(CursoRequest request) {
        Profesor profesor = profesorRepository.findById(request.idProfesor())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        Curso curso = cursoMapper.toEntity(request, profesor);

        return cursoMapper.toResponse(cursoRepository.save(curso));
    }

    @Override
    @Transactional
    public InscripcionResponse asociarEstudiante(InscripcionRequest request) {
        Estudiante estudiante = estudianteRepository.findById(request.idEstudiante())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Curso curso = cursoRepository.findWithProfesorAndEstudiante(request.idCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        if (curso.getEstudiantes().contains(estudiante)) {
            throw new RuntimeException("El estudiante ya está inscrito en este curso");
        }

        curso.getEstudiantes().add(estudiante);

        Curso cursoNuevoEstudiante = cursoRepository.save(curso);


        return cursoMapper.toInscripcionResponse(cursoNuevoEstudiante, estudiante);
    }

    @Override
    @Transactional
    public CursoResponse update(Long id, CursoRequest request) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Profesor profesor = profesorRepository.findById(request.idProfesor())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        cursoMapper.updateEntity(curso, request, profesor);

        return cursoMapper.toResponse(cursoRepository.save(curso));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        cursoRepository.delete(curso);

    }
}
