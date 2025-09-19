package com.dohuy.jpapart2.dto;

import com.dohuy.jpapart2.entity.Course;
import com.dohuy.jpapart2.entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentCourseDTO {
    private Long id;
    private List<StudentDTO> studentDTOList;
    private List<CourseDTO> courseDTOList;

    public static StudentCourseDTO toDTO(List<Student> students, List<Course> courses) {
        List<StudentDTO> studentDTOList = (students != null)
                ? students.stream()
                .map(StudentDTO::toDTO)
                .toList()
                : null;

        List<CourseDTO> courseDTOList = (courses != null)
                ? courses.stream()
                .map(CourseDTO::toDTO)
                .toList()
                : null;

        return StudentCourseDTO.builder()
                .studentDTOList(studentDTOList)
                .courseDTOList(courseDTOList)
                .build();
    }
}
