package com.example.studentmanagementsystem.dto;

import com.example.studentmanagementsystem.models.Student;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class StudentDtoGet {
  private Long id;
  private String names;
  private String location;
  private String email;

  public StudentDtoGet(Student student){
      BeanUtils.copyProperties(student,this);
      location = student.getAddress().getLocation();
      email = student.getAddress().getEmail();
  }

}
