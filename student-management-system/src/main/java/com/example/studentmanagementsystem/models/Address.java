package com.example.studentmanagementsystem.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String location;
    @NotNull
    private String email;

    public Address(String location,String email){
        this.location = location;
        this.email = email;
    }

    @NotNull
    @OneToOne
    @PrimaryKeyJoinColumn
    private Student student;
}
