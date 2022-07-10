package com.example.studentmanagementsystem.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String names;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    public Student(String names,Address address){
        this.names = names;
        this.address = address;
    }

    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    public void enroll(Course course){
        courses.add(course);
    }


}
