package com.dohuy.jpapart2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_course")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "courseId")
    private Course course;
}
