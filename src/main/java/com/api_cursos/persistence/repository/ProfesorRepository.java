package com.api_cursos.persistence.repository;

import com.api_cursos.persistence.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    @Query("SELECT p FROM Profesor p LEFT JOIN FETCH p.cursos WHERE p.id = :id")
    Optional<Profesor> findWithCursos(@Param("id") Long id);
}
