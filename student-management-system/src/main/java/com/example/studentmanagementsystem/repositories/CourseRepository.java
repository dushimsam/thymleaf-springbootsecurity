package com.example.studentmanagementsystem.repositories;

import com.example.studentmanagementsystem.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course getCourseByName(String name);
}
