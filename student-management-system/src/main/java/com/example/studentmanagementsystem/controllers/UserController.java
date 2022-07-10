package com.example.studentmanagementsystem.controllers;

import com.example.studentmanagementsystem.dto.UserRegistrationDtoPost;
import com.example.studentmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserController {

    @Autowired
    private UserService userService;


    @ModelAttribute("user")
    public UserRegistrationDtoPost userRegistrationDto() {
        return new UserRegistrationDtoPost();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDtoPost registrationDto) {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
