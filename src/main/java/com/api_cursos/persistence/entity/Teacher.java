package com.api_cursos.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teachers")
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String dni;
    private String name;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String specialization;
    private String phone;

    // Un teacher dicta muchos cursos
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();

}
