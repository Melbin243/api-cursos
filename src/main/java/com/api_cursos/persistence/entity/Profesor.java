package com.api_cursos.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profesores")
@Builder
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String dni;
    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String email;
    private String profesion;
    private String telefono;

    // Un profesor dicta muchos cursos
    @OneToMany(mappedBy = "profesor")
    private List<Curso> cursos = new ArrayList<>();

}
