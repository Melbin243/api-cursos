package com.api_cursos.mapper;

import com.api_cursos.dto.teacher.TeacherDetailResponse;
import com.api_cursos.dto.teacher.TeacherRequest;
import com.api_cursos.dto.teacher.TeacherResponse;
import com.api_cursos.persistence.entity.Course;
import com.api_cursos.persistence.entity.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    public TeacherResponse toResponse(Teacher teacher) {
        if (teacher == null) return null;

        return new TeacherResponse(
                teacher.getId(),
                teacher.getDni(),
                teacher.getName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getSpecialization(),
                teacher.getPhone()
        );
    }

    public TeacherDetailResponse toDetailResponse(Teacher teacher) {
        if (teacher == null) return null;

        return new TeacherDetailResponse(
                teacher.getId(),
                teacher.getDni(),
                teacher.getName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getSpecialization(),
                teacher.getPhone(),
                teacher.getCourses().stream()
                        .map(Course::getName)
                        .toList()
        );
    }

    public Teacher toEntity(TeacherRequest request) {
        if (request == null) return null;

        Teacher teacher = new Teacher();
        teacher.setDni(request.dni());
        teacher.setName(request.name());
        teacher.setLastName(request.lastName());
        teacher.setEmail(request.email());
        teacher.setSpecialization(request.specialization());
        teacher.setPhone(request.phone());

        return teacher;
    }

    public void updateEntity(TeacherRequest newTeacher, Teacher oldTeacher) {
        if (newTeacher == null || oldTeacher == null) return;

        if (newTeacher.dni() != null) {
            oldTeacher.setDni(newTeacher.dni());
        }
        if (newTeacher.name() != null) {
            oldTeacher.setName(newTeacher.name());
        }
        if (newTeacher.lastName() != null) {
            oldTeacher.setLastName(newTeacher.lastName());

        }
        if (newTeacher.email() != null) {
            oldTeacher.setEmail(newTeacher.email());

        }
        if (newTeacher.specialization() != null) {
            oldTeacher.setSpecialization(newTeacher.specialization());

        }
        if (newTeacher.phone() != null) {
            oldTeacher.setPhone(newTeacher.phone());

        }

    }

}
