package com.api_cursos.persistence.repository;

import com.api_cursos.persistence.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT c FROM Curso c LEFT JOIN FETCH c.profesor")
    List<Curso> findAllWithProfesor();

    @Query("SELECT c FROM Curso c " +
    "LEFT JOIN FETCH c.profesor " +
    "LEFT JOIN FETCH c.estudiantes WHERE c.id = :id")
    Optional<Curso> findWithProfesorAndEstudiante(@Param("id") Long id);
}
