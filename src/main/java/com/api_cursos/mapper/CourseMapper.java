package com.api_cursos.mapper;

import com.api_cursos.dto.course.*;
import com.api_cursos.persistence.entity.Course;
import com.api_cursos.persistence.entity.Student;
import com.api_cursos.persistence.entity.Teacher;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseResponse toResponse(Course course) {
        if (course == null) return null;

        return new CourseResponse(
                course.getId(),
                course.getName(),
                course.getDescription(),
                getTeacherFullName(course.getTeacher())
        );
    }

    public CourseDetailResponse toCourseDetailResponse(Course course) {
        if (course == null) return null;


        return new CourseDetailResponse(
                course.getId(),
                course.getName(),
                course.getDescription(),
                getTeacherFullName(course.getTeacher()),
                course.getStudents().stream()
                        .map(s -> new StudentSimpleResponse(
                                s.getId(), s.getName(), s.getLastName()
                        )).toList()
        );
    }

    public Course toEntity(CourseRequest request, Teacher teacher) {
        if (request == null) return null;

        Course course = new Course();
        course.setName(request.name());
        course.setDescription(request.description());
        course.setTeacher(teacher);

        return course;
    }

    public EnrollmentResponse toEnrollmentResponse(Course course, Student student) {
        if (course == null || student == null) return null;

        return new EnrollmentResponse(
                course.getId(),
                course.getName(),
                course.getDescription(),
                getTeacherFullName(course.getTeacher()),
                toStudentSimpleResponse(student)
        );
    }

    public void updateEntity(Course oldCourse, CourseRequest newCourse, Teacher teacher) {
        if (oldCourse == null || newCourse == null) return;

        oldCourse.setName(newCourse.name());
        oldCourse.setDescription(newCourse.description());
        oldCourse.setTeacher(teacher);
    }

    private String getTeacherFullName(Teacher teacher) {
        if (teacher == null) return "Profesor no asignado";

        return teacher.getName() + " " + teacher.getLastName();
    }

    private StudentSimpleResponse toStudentSimpleResponse(Student student) {
        if (student == null) return null;

        return new StudentSimpleResponse(
                student.getId(),
                student.getName(),
                student.getLastName()
        );
    }

}
