package com.example.studentmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDtoPost {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
