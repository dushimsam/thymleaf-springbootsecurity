package com.example.studentmanagementsystem.repositories;

import com.example.studentmanagementsystem.models.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    boolean existsById(Long id);
}
