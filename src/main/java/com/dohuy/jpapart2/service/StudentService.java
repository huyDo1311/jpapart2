package com.dohuy.jpapart2.service;

import com.dohuy.jpapart2.dto.StudentDTO;
import com.dohuy.jpapart2.entity.Course;
import com.dohuy.jpapart2.entity.Student;
import com.dohuy.jpapart2.entity.StudentCourse;
import com.dohuy.jpapart2.exception.EmailExitsException;
import com.dohuy.jpapart2.exception.EntityNotFoundException;
import com.dohuy.jpapart2.repository.CourseRepository;
import com.dohuy.jpapart2.repository.StudentRepository;
import com.dohuy.jpapart2.request.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public ResponseEntity<?> create(StudentRequest request){

        if (studentRepository.existsByEmail(request.getEmail())) {
           throw new EmailExitsException("Email already exists: ");
        }

        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());

        studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(StudentDTO.toDTO(student));
    }

    public ResponseEntity<?> getAll() {
        List<StudentDTO> students = studentRepository.findAll()
                .stream()
                .map(StudentDTO::toDTOWithCourse)
                .toList();
        return ResponseEntity.ok(students);
    }

    public ResponseEntity<?> addCourseToStudent(Long id, Long courseId) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Student not found")
        );
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new EntityNotFoundException("Course not found")
        );

        boolean exists = student.getStudentCourses().stream()
                .anyMatch(sc -> sc.getCourse().getId().equals(courseId));
        if (exists) {
            return ResponseEntity.badRequest()
                    .body("Student is already enrolled in this course.");
        }

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        student.getStudentCourses().add(studentCourse);

        // Lưu student (cascade = ALL sẽ lưu cả StudentCourse)
//        studentRepository.save(student);

        // Trả về DTO với danh sách course
        StudentDTO studentDTO = StudentDTO.toDTOWithCourse(student);
        return ResponseEntity.ok(studentDTO);
    }

    public ResponseEntity<?> getCoursesByStudentId(Long id){
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Student not found")
        );

        StudentDTO studentDTO = StudentDTO.toDTOWithCourse(student);
        return ResponseEntity.ok(studentDTO);
    }
}
