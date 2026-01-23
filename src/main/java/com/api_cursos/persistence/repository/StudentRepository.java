package com.api_cursos.persistence.repository;

import com.api_cursos.persistence.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.id = :id")
    Optional<Student> findByIdWithCourses(@Param("id") Long id);
}
