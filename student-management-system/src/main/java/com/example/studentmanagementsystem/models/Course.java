package com.example.studentmanagementsystem.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    public Course(String assignedCourseName) {
        this.name = assignedCourseName;
    }

    @ManyToMany
    @JoinTable(name="students_courses",joinColumns={@JoinColumn(name = "course_id")},inverseJoinColumns={@JoinColumn(name = "student_id")})
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    @JoinTable(name = "instructor_course",joinColumns={@JoinColumn(name = "instructor_id")},inverseJoinColumns={@JoinColumn(name = "course_id")})
    private Instructor instructor;

}
