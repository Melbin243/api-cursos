package com.api_cursos.persistence.repository;

import com.api_cursos.persistence.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.teacher")
    List<Course> findAllWithProfesor();

    @Query("SELECT c FROM Course c " +
    "LEFT JOIN FETCH c.teacher " +
    "LEFT JOIN FETCH c.students WHERE c.id = :id")
    Optional<Course> findWithProfesorAndEstudiante(@Param("id") Long id);
}
