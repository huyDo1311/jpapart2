package com.dohuy.jpapart2.repository;

import com.dohuy.jpapart2.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    boolean existsByTitle(String title);
}
