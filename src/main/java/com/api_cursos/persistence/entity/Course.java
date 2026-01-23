package com.api_cursos.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


    @ManyToMany
    @JoinTable(
            name = "courses_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();
}
