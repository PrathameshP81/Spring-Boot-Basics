package com.EmployeeManagement.EmployeeManagementSystem.Controller;

import com.EmployeeManagement.EmployeeManagementSystem.Entity.Department;
import com.EmployeeManagement.EmployeeManagementSystem.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/Department")

public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/create")
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.CreateDepartment(department);
    }

    @GetMapping("/getAll")
    public List<Department> getAllDepartments() {
        return  departmentService.GetDepartments();
    }

    @GetMapping("/getById/{deptid}")
    public Department getDepartmentById(@PathVariable int deptid) {
        return departmentService.getDepartmentById(deptid);
    }

    @DeleteMapping("/delete")
    public String deleteDepartment(@RequestBody Department department) {
        return departmentService.deleteDepartment(department);
    }
    @PutMapping("/update")
    public Department updateDepartment(@RequestBody Department department) {
        return departmentService.updateDepartment(department);
    }
}
