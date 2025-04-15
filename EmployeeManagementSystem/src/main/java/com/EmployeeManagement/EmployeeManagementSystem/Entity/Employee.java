package com.EmployeeManagement.EmployeeManagementSystem.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.StringTokenizer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    int empid;

    @NotBlank(message = "Employee Name is Required Field")
    @Column(nullable = false)
    String name;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Employee Email is Required Field")
    String email;

    @Column(nullable = false)
    long salary;

    @Column(nullable = false)
    String gender;

    @Column(nullable = false , length = 50)
    @NotBlank(message = "Employee Address is Required Field")
    String address;

    @Column(nullable = false , length = 10)
    long phoneNumber;

    @Column(nullable = false)
    @NotBlank(message = "Employee Profile Pic is Required Field")
    String profilePic;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    Department department;

}



