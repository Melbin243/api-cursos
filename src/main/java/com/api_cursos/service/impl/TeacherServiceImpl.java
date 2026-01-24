package com.api_cursos.service.impl;

import com.api_cursos.common.exception.ResourceNotFoundException;
import com.api_cursos.dto.teacher.TeacherDetailResponse;
import com.api_cursos.dto.teacher.TeacherRequest;
import com.api_cursos.dto.teacher.TeacherResponse;
import com.api_cursos.mapper.TeacherMapper;
import com.api_cursos.persistence.entity.Teacher;
import com.api_cursos.persistence.repository.TeacherRepository;
import com.api_cursos.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;


    @Override
    public List<TeacherResponse> getAll() {
        List<Teacher> teachers = teacherRepository.findAll();

        return teachers.stream()
                .map(teacherMapper::toResponse)
                .toList();
    }

    @Override
    public TeacherDetailResponse getById(Long id) {
        Teacher teacher = teacherRepository.findByIdWithCourses(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado"));

        return teacherMapper.toDetailResponse(teacher);
    }

    @Override
    @Transactional
    public TeacherResponse create(TeacherRequest request) {
        Teacher teacher = teacherMapper.toEntity(request);

        return teacherMapper.toResponse(teacherRepository.save(teacher));
    }

    @Override
    @Transactional
    public TeacherResponse update(Long id, TeacherRequest request) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado"));

        teacherMapper.updateEntity(request, teacher);

        return teacherMapper.toResponse(teacherRepository.save(teacher));
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado"));

        // Desvincular teacher de los cursos
        teacher.getCourses().forEach(course -> course.setTeacher(null));

        teacherRepository.delete(teacher);

    }
}
