package com.example.studentmanagementsystem.services;

import com.example.studentmanagementsystem.dto.*;
import com.example.studentmanagementsystem.exception.ApiRequestException;
import com.example.studentmanagementsystem.exception.NotFoundException;
import com.example.studentmanagementsystem.models.Course;
import com.example.studentmanagementsystem.models.Instructor;
import com.example.studentmanagementsystem.repositories.CourseRepository;
import com.example.studentmanagementsystem.repositories.InstructorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorService {
   @Autowired
   private InstructorRepository instructorRepository;

   @Autowired
   private CourseRepository courseRepository;

   public  ResponseEntity<?> add(InstructorDtoPost instructorDtoPost){

      Course existsCourse = courseRepository.getCourseByName(instructorDtoPost.getAssignedCourseName());
      if(existsCourse != null){
          return new ResponseEntity<>(new ApiRequestException("course already taken"), HttpStatus.BAD_REQUEST);
      }
         Course course = new Course(instructorDtoPost.getAssignedCourseName());
         Instructor instructor = new Instructor(instructorDtoPost.getNames());
         instructor.addCourse(course);
         return  new ResponseEntity<>(new InstructorDtoGet(instructorRepository.save(instructor)),HttpStatus.CREATED);
   }

   public InstructorDtoGet addNewCourse(Long instructorId,String courseName) {
      Course course = new Course(courseName);
      Instructor instructor = instructorRepository.findById(instructorId).orElseThrow();
      instructor.addCourse(course);
      return  new InstructorDtoGet(instructorRepository.save(instructor));
   }
   public InstructorDtoGet get(Long id){
       Instructor instructor = instructorRepository.findById(id).orElseThrow();
       return new InstructorDtoGet(instructor);
   }

   public ResponseEntity<?> delete(long id){
      Instructor instructor = instructorRepository.findById(id).orElseThrow(()->new NotFoundException("Instructor"));
      instructorRepository.deleteById(id);
      return new ResponseEntity<>(instructor,HttpStatus.OK);
   }

   public List<InstructorDtoGet> getAll(){
      List<Instructor> instructors = instructorRepository.findAll();
      return getFormattedData(instructors);
   }
   public ResponseEntity<?> update(Long id, Instructor newInstructor){
      if(!instructorRepository.existsById(id)){
        return new ResponseEntity<>(new NotFoundException("instructor"),HttpStatus.NOT_FOUND);
      }

      Instructor instructor = instructorRepository.findById(id).get();
      instructor.setNames(newInstructor.getNames());
      return  new ResponseEntity<>(new InstructorDtoGet(instructorRepository.save(instructor)),HttpStatus.OK);
   }

   public List<InstructorDtoGet> getFormattedData(List<Instructor> instructors){
      List<InstructorDtoGet> list = new ArrayList<>();
      for(Instructor instructor:instructors){
         list.add(new InstructorDtoGet(instructor));
      }
      return list;
   }
}
