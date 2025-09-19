package com.dohuy.jpapart2.controller;

import com.dohuy.jpapart2.request.StudentRequest;
import com.dohuy.jpapart2.service.CourseService;
import com.dohuy.jpapart2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StudentRequest request){
        return studentService.create(request);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return studentService.getAll();
    }

    @PostMapping("/{id}/courses/{courseId}")
    public ResponseEntity<?> addCourseToStudent(@PathVariable Long id,@PathVariable Long courseId){
        return studentService.addCourseToStudent(id,courseId);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<?> getCoursesByStudentId(@PathVariable Long id){
        return studentService.getCoursesByStudentId(id);
    }

}
