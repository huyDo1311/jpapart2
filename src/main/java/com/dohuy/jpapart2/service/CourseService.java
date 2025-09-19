package com.dohuy.jpapart2.service;

import com.dohuy.jpapart2.dto.CourseDTO;
import com.dohuy.jpapart2.entity.Course;
import com.dohuy.jpapart2.exception.ExitsException;
import com.dohuy.jpapart2.repository.CourseRepository;
import com.dohuy.jpapart2.repository.StudentRepository;
import com.dohuy.jpapart2.request.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<?> createCourse(CourseRequest request){
        if(courseRepository.existsByTitle(request.getTitle())){
           throw  new ExitsException("Title already exists");
        }

        Course course = new Course();
        course.setTitle(request.getTitle());

        courseRepository.save(course);

        CourseDTO courseDTO = CourseDTO.toDTOWithStudent(course);

        return ResponseEntity.status(HttpStatus.CREATED).body(courseDTO);
    }
}
