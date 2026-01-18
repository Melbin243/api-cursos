package com.api_cursos.service;

import com.api_cursos.dto.profesor.ProfesorDetalleResponse;
import com.api_cursos.dto.profesor.ProfesorRequest;
import com.api_cursos.dto.profesor.ProfesorResponse;

import java.util.List;

public interface ProfesorService {
    List<ProfesorResponse> getAll();
    ProfesorDetalleResponse getOne(Long id);
    ProfesorResponse create(ProfesorRequest request);
    ProfesorResponse update(Long id, ProfesorRequest request);
    void delete(Long id);
}
