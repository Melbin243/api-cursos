package com.api_cursos.service.impl;

import com.api_cursos.dto.student.StudentDetailResponse;
import com.api_cursos.dto.student.StudentRequest;
import com.api_cursos.dto.student.StudentResponse;
import com.api_cursos.mapper.StudentMapper;
import com.api_cursos.persistence.entity.Course;
import com.api_cursos.persistence.entity.Student;
import com.api_cursos.persistence.repository.CourseRepository;
import com.api_cursos.persistence.repository.StudentRepository;
import com.api_cursos.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CourseRepository courseRepository;

    @Override
    public List<StudentResponse> getAll() {
        List<Student> student = studentRepository.findAll();

        return student.stream()
                .map(studentMapper::toResponse).toList();
    }

    @Override
    public StudentDetailResponse getById(Long id) {
        Student student = studentRepository.findByIdWithCourses(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        return studentMapper.toDetailResponse(student);
    }

    @Override
    @Transactional
    public StudentResponse create(StudentRequest request) {
        Student newStudent = studentMapper.toEntity(request);

        if (request.courseIds() != null ) {
            for (Long id : request.courseIds()) {
                Course course = courseRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Curso " + id + " no encontrado"));

                newStudent.getCourses().add(course);
            }
        }

        return studentMapper.toResponse(
                studentRepository.save(newStudent)
        );
    }

    @Override
    @Transactional
    public StudentResponse update(Long id, StudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        studentMapper.updateEntity(request, student);

        if (request.courseIds() != null) {
            List<Course> newCourses = courseRepository.findAllById(request.courseIds());
            student.setCourses(newCourses);
        }

        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public void delete(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante " + id + " no encontrado"));

        studentRepository.delete(student);
    }
}
