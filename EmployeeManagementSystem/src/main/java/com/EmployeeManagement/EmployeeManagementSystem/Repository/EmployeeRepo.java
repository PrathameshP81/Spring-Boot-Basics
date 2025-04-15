package com.EmployeeManagement.EmployeeManagementSystem.Repository;

import com.EmployeeManagement.EmployeeManagementSystem.Entity.Department;
import com.EmployeeManagement.EmployeeManagementSystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
