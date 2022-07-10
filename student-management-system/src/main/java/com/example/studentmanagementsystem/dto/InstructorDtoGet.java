package com.example.studentmanagementsystem.dto;

import com.example.studentmanagementsystem.models.Course;
import com.example.studentmanagementsystem.models.Instructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class InstructorDtoGet {
    private long id;
    private String names;
    private List<String> courses = new ArrayList<>();
    public InstructorDtoGet(Instructor instructor){
        BeanUtils.copyProperties(instructor,this);

        for(Course course: instructor.getCourses()){
            courses.add(course.getName());
        }
    }

}
