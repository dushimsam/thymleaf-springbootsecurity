package com.example.studentmanagementsystem.services;

import com.example.studentmanagementsystem.dto.StudentDtoGet;
import com.example.studentmanagementsystem.dto.StudentDtoPost;
import com.example.studentmanagementsystem.models.Address;
import com.example.studentmanagementsystem.models.Course;
import com.example.studentmanagementsystem.models.Student;
import com.example.studentmanagementsystem.repositories.AddressRepository;
import com.example.studentmanagementsystem.repositories.CourseRepository;
import com.example.studentmanagementsystem.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AddressRepository addressRepository;

    public StudentDtoGet add(StudentDtoPost studentDtoPost){
        Address stdAdd = new Address();
        Student std = new Student();
        BeanUtils.copyProperties(studentDtoPost,stdAdd);
        BeanUtils.copyProperties(studentDtoPost,std);

        std.setAddress(stdAdd);

        return new StudentDtoGet(studentRepository.save(std));
    }

    public StudentDtoGet get(Long id){
        Student std = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        return  new StudentDtoGet(std);
    }

    public StudentDtoGet update(StudentDtoPost studentDtoPost,Long id){
        Address stdAdd = new Address();
        Student std = studentRepository.findById(id).orElseThrow(()-> new RuntimeException());
        BeanUtils.copyProperties(studentDtoPost,stdAdd);
        BeanUtils.copyProperties(studentDtoPost,std);
        std.setAddress(stdAdd);
        return new StudentDtoGet(studentRepository.save(std));
    }

    public List<StudentDtoGet> getAll(){
        List<Student> students = studentRepository.findAll();
        return getFormatted(students);
    }

    public StudentDtoGet delete(Long id){
        Student std = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        studentRepository.delete(std);
        return new StudentDtoGet(std);
    }

    public StudentDtoGet enrollStudent(Long studentId, Long courseId){
        Course course = courseRepository.findById(courseId).orElseThrow(RuntimeException::new);
        Student student = studentRepository.findById(studentId).orElseThrow(RuntimeException::new);
        student.enroll(course);
        return new StudentDtoGet(studentRepository.save(student));
    }


    public List<StudentDtoGet> getFormatted(List<Student> students){
        List<StudentDtoGet> studentDtoGetList = new ArrayList<>();

        for(Student std:students){
            studentDtoGetList.add(new StudentDtoGet(std));
        }
        return studentDtoGetList;
    }

}
