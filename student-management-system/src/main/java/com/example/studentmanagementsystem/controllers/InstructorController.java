package com.example.studentmanagementsystem.controllers;

import com.example.studentmanagementsystem.dto.InstructorDtoGet;
import com.example.studentmanagementsystem.dto.InstructorDtoPost;
import com.example.studentmanagementsystem.models.Instructor;
import com.example.studentmanagementsystem.services.InstructorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "instructors")
@AllArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping
    public String getAll(Model model) {
        List<InstructorDtoGet> instructors = instructorService.getAll();
        model.addAttribute("listInstructors",instructors);
        return "instructors";
    }

    @PostMapping(path = "/saveInstructor")
    public String saveInstructor(@ModelAttribute("instructor") InstructorDtoPost instructor,Model model){
       ResponseEntity responseEntity = instructorService.add(instructor);
       if(responseEntity.getStatusCode() == HttpStatus.CREATED)
          return "redirect:/instructors";

       model.addAttribute("exception",responseEntity.getBody());
       return "exceptionPageHandler";
    }

    @GetMapping(path="register")
    public String registerInstructorForm(Model model)  {
       InstructorDtoPost instructorDtoPost = new InstructorDtoPost();
       model.addAttribute("instructor",instructorDtoPost);
        return "newInstructor";
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<InstructorDtoGet> get(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<InstructorDtoGet>(instructorService.get(id),HttpStatus.OK);
    }

    @PutMapping(path="/instructor/{instructorId}/course/{courseName}")
    public ResponseEntity<InstructorDtoGet> enrollStudent(
            @PathVariable("instructorId") Long instructorId, @PathVariable("courseName") String courseName) {
        return   new ResponseEntity<InstructorDtoGet>(instructorService.addNewCourse(instructorId,courseName),HttpStatus.OK);
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteInstructor(@PathVariable (value = "id") long id){
       ResponseEntity<?> responseEntity =  instructorService.delete(id);
       if(responseEntity.getStatusCode() == HttpStatus.OK)
           return "redirect:/instructors";
       return "exceptionPageHandler";
    }



    @PostMapping("/updateInstructor")
    public String updateInstructor(@ModelAttribute("instructor") Instructor instructor,Model model) {

        ResponseEntity<?> responseEntity = instructorService.update(instructor.getId(), instructor);
        if(responseEntity.getStatusCode() == HttpStatus.OK)
            return "redirect:/instructors";

        model.addAttribute("exception",responseEntity.getBody());
        return "exceptionPageHandler";
    }


    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

        // get employee from the service
        InstructorDtoGet instructor = instructorService.get(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("instructor", instructor);
        return "updateInstructor";
    }

}
