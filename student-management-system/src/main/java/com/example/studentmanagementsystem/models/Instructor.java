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
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id")
    private long id;

    @NotNull
    private String names;

    public Instructor(String names){
        this.names = names;
    }

    @OneToMany(mappedBy = "instructor",cascade=CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

   public void addCourse(Course course){
        courses.add(course);
    }

}
