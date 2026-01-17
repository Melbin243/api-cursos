package com.api_cursos.persistence.repository;

import com.api_cursos.persistence.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    @Query("SELECT e FROM Estudiante e LEFT JOIN FETCH e.cursos WHERE e.id = :id")
    Optional<Estudiante> findOneWithCursos(@Param("id") Long id);
}
