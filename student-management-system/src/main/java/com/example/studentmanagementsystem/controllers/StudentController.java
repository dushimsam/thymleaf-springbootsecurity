package com.example.studentmanagementsystem.controllers;

import com.example.studentmanagementsystem.dto.StudentDtoGet;
import com.example.studentmanagementsystem.dto.StudentDtoPost;
import com.example.studentmanagementsystem.models.Student;
import com.example.studentmanagementsystem.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public String getAll(Model model) {
        List<StudentDtoGet> students = studentService.getAll();
        return "students";
    }

    @GetMapping("/register-student")
    public String add()  {
//        return new ResponseEntity<StudentDtoGet>(studentService.add(dto),HttpStatus.CREATED);
        return "register_student";
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<StudentDtoGet> get(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<StudentDtoGet>(studentService.get(id),HttpStatus.OK);
    }

    @PutMapping(path="/student/{studentId}/course/{courseId}")
    public ResponseEntity<StudentDtoGet> enrollStudent(
            @PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId) {
        return   new ResponseEntity<StudentDtoGet>(studentService.enrollStudent(studentId,courseId),HttpStatus.OK);
    }

}
