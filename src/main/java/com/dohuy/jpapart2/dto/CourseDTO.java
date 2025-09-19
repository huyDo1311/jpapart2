package com.dohuy.jpapart2.dto;

import com.dohuy.jpapart2.entity.Course;
import com.dohuy.jpapart2.entity.Student;
import com.dohuy.jpapart2.entity.StudentCourse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;
    private String title;
    private List<StudentDTO> students;

    public static CourseDTO toDTO(Course course){
        return CourseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .build();
    }

    public static CourseDTO toDTOWithStudent(Course course){
        CourseDTO courseDTO = CourseDTO.toDTO(course);

        if(course.getStudentCourses() != null){
            List<StudentDTO> studentDTOList = course.getStudentCourses().stream()
                    .map(StudentCourse::getStudent)
                    .map(StudentDTO::toDTO)
                    .toList();
            courseDTO.setStudents(studentDTOList);
        }

        return courseDTO;
    }

}
