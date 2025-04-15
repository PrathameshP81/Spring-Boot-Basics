package com.EmployeeManagement.EmployeeManagementSystem.Service;

import com.EmployeeManagement.EmployeeManagementSystem.Entity.Department;
import com.EmployeeManagement.EmployeeManagementSystem.Exception.DepartmentNotFound;
import com.EmployeeManagement.EmployeeManagementSystem.Repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepo departmentRepo;

    public Department CreateDepartment(Department department) {

        Department newDepartment = null;
        try{

            if(department == null && department.equals(departmentRepo.findById(department.getDeptid()))) {
                throw new Exception("Department already exists");
            }else{
                newDepartment = departmentRepo.save(department);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return newDepartment;
    }

    public List<Department> GetDepartments() {
        List<Department> departments = new ArrayList<Department>();
        try{
            departments = departmentRepo.findAll();

        }catch (Exception e){
            System.out.println(e);
        }
        return departments;
    }

    public Department updateDepartment(Department department) {
        Department newDepartment = null;

        try{
            newDepartment = departmentRepo.findById(department.getDeptid()).orElseThrow(()-> new DepartmentNotFound("Department not found"));
            newDepartment.setDeptname(department.getDeptname());
            newDepartment.setDeptdesc(department.getDeptdesc());
            newDepartment.setDeptHead(department.getDeptHead());
            newDepartment.setDeptTel(department.getDeptTel());
            departmentRepo.save(newDepartment);

        }catch (Exception e){
            System.out.println(e);
        }
        return newDepartment;
    }

    public String deleteDepartment(Department department) {
        try{
            departmentRepo.delete(department);
            return "Department deleted successfully";
        }catch (Exception e){
            System.out.println(e);
        }
        return "OOps ! Something went wrong";
    }

    public Department getDepartmentById(int deptid) {
        Department department = null;

        try{
            department = departmentRepo.findById(deptid).orElseThrow(()->new DepartmentNotFound("Department not Found"));
        }catch (Exception e){
            System.out.println(e);
        }
        return department;
    }

}
