package com.api_cursos.service;

import com.api_cursos.dto.curso.*;
import com.api_cursos.persistence.entity.Curso;

import java.util.List;

public interface CursoService {

    List<CursoResponse> getAll();
    CursoDetalleResponse getOne(Long id);
    CursoResponse create(CursoRequest request);
    InscripcionResponse asociarEstudiante(InscripcionRequest request);
    CursoResponse update(Long id, CursoRequest request);
    void delete(Long id);


}
