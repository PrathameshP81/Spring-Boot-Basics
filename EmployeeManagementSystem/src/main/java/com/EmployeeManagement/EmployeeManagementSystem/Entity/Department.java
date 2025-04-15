package com.EmployeeManagement.EmployeeManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int deptid;

    @Column(nullable = false)
    String deptname;

    @Column(length = 150 , nullable = false)
    String deptdesc;

    @Column(nullable = false)
    String deptHead;

    @Column(length = 10 ,nullable = false)
    long deptTel;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @JsonIgnore
    List<Employee> employee;
}
