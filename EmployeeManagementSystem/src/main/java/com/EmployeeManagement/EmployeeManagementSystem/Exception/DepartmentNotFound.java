package com.EmployeeManagement.EmployeeManagementSystem.Exception;

public class DepartmentNotFound extends RuntimeException {
    public DepartmentNotFound(String departmentNotFound) {
        super(departmentNotFound);
    }
}
