package com.EmployeeManagement.EmployeeManagementSystem.Service;

import com.EmployeeManagement.EmployeeManagementSystem.Repository.DepartmentRepo;
import com.EmployeeManagement.EmployeeManagementSystem.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetadataService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    public int GetEmployeeCount() throws Exception {
        return (int) employeeRepo.count();
    }
    public int GetDepartmentCount() throws Exception {
        return (int) departmentRepo.count();
    }
}
