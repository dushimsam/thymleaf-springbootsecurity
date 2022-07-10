package com.example.studentmanagementsystem.repositories;

import com.example.studentmanagementsystem.models.Address;
import com.example.studentmanagementsystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
