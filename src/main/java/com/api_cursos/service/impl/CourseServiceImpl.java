package com.api_cursos.service.impl;

import com.api_cursos.common.exception.AlreadyEnrolledException;
import com.api_cursos.common.exception.ResourceNotFoundException;
import com.api_cursos.dto.course.*;
import com.api_cursos.mapper.CourseMapper;
import com.api_cursos.persistence.entity.Course;
import com.api_cursos.persistence.entity.Student;
import com.api_cursos.persistence.entity.Teacher;
import com.api_cursos.persistence.repository.CourseRepository;
import com.api_cursos.persistence.repository.StudentRepository;
import com.api_cursos.persistence.repository.TeacherRepository;
import com.api_cursos.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<CourseResponse> getAll() {
        List<Course> courses = courseRepository.findAllWithProfesor();

        return courses.stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    @Override
    public CourseDetailResponse getById(Long id) {
        Course course = courseRepository.findWithProfesorAndEstudiante(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        return courseMapper.toCourseDetailResponse(course);
    }

    @Override
    @Transactional
    public CourseResponse create(CourseRequest request) {
        Teacher teacher = teacherRepository.findById(request.teacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado"));

        Course course = courseMapper.toEntity(request, teacher);

        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    @Transactional
    public EnrollmentResponse enrollStudent(EnrollmentRequest request) {
        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

        Course course = courseRepository.findWithProfesorAndEstudiante(request.courseId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        if (course.getStudents().contains(student)) {
            throw new AlreadyEnrolledException(course.getId(), student.getId());
        }

        course.getStudents().add(student);

        Course updatedCourse = courseRepository.save(course);


        return courseMapper.toEnrollmentResponse(updatedCourse, student);
    }

    @Override
    @Transactional
    public CourseResponse update(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        Teacher teacher = teacherRepository.findById(request.teacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado"));

        courseMapper.updateEntity(course, request, teacher);

        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        courseRepository.delete(course);

    }
}
