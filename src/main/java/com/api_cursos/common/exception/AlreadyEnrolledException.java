package com.api_cursos.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyEnrolledException extends RuntimeException{

    private final Long courseId;
    private final Long studentId;

    public AlreadyEnrolledException(Long courseId, Long studentId) {
        super(String.format("AlreadyEnrolledException: Student with id=%d already enrolled in course id=%d", courseId, studentId));
        this.courseId = courseId;
        this.studentId = studentId;
    }
}
