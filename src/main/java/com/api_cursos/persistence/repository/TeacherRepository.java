package com.api_cursos.persistence.repository;

import com.api_cursos.persistence.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM Teacher t LEFT JOIN FETCH t.courses WHERE t.id = :id")
    Optional<Teacher> findByIdWithCourses(@Param("id") Long id);
}
