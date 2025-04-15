package com.EmployeeManagement.EmployeeManagementSystem.Repository;

import com.EmployeeManagement.EmployeeManagementSystem.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}
