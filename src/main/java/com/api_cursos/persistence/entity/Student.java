package com.api_cursos.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;

    @Column(unique = true)
    private String email;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();
}
