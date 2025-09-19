package com.dohuy.jpapart2.dto;

import com.dohuy.jpapart2.entity.Student;
import com.dohuy.jpapart2.entity.StudentCourse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private List<CourseDTO> course;

    public static StudentDTO toDTO(Student student){
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .build();
    }

    public static StudentDTO toDTOWithCourse(Student student){
        StudentDTO studentDTO = StudentDTO.toDTO(student);

        if(student.getStudentCourses() != null){
            List<CourseDTO> courseDTOList = student.getStudentCourses().stream()
                    .map(StudentCourse::getCourse)
                    .map(CourseDTO::toDTO)
                    .toList();
            studentDTO.setCourse(courseDTOList);
        }

        return studentDTO;
    }
}
