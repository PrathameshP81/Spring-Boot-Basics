package com.EmployeeManagement.EmployeeManagementSystem.Service;

import com.EmployeeManagement.EmployeeManagementSystem.ApiResponse.ApiResponse;
import com.EmployeeManagement.EmployeeManagementSystem.Entity.Department;
import com.EmployeeManagement.EmployeeManagementSystem.Entity.Employee;
import com.EmployeeManagement.EmployeeManagementSystem.Exception.DepartmentNotFound;
import com.EmployeeManagement.EmployeeManagementSystem.Exception.UserDatailsNotFoundException;
import com.EmployeeManagement.EmployeeManagementSystem.Repository.DepartmentRepo;
import com.EmployeeManagement.EmployeeManagementSystem.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    FileService fileService;

    @Value("${file.upload-dir}")
    private String basePath;


    public ResponseEntity<ApiResponse<Employee>> create(Employee emp, MultipartFile file) throws Exception {

        String fileName = fileService.uploadFile(basePath, file);
        String filepath = "http://localhost:3000/file/" + fileName;

        emp.setProfilePic(filepath);

        Department department = departmentRepo.findById(emp.getDepartment().getDeptid())
                .orElseThrow(() -> new DepartmentNotFound("Department With Give Id is not found"));

        emp.setDepartment(department);
        employeeRepo.save(emp);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Employee Created", emp));
    }

    public List<Employee> getAll() throws Exception {
        return employeeRepo.findAll();
    }

    public ResponseEntity<ApiResponse<Employee>> getEmployeeById(int id) throws Exception {

        Employee employee;

        employee = employeeRepo.findById(id).get();

        if(employee != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Employee Found", employee));
        }else {
            throw new UserDatailsNotFoundException("Employee Not Found");
        }
    }

    public ResponseEntity<ApiResponse<Employee>> update(MultipartFile file , Employee emp) throws Exception {

        Employee employee = employeeRepo.findById(emp.getEmpid()).get();

        if(employee == null) {
            throw new UserDatailsNotFoundException("Employee Not Found");
        }

        String fileName = fileService.uploadFile(basePath, file);
        String filepath = "http://localhost:3000/file/" + fileName;


        if(employeeRepo.existsById(emp.getEmpid())) {
            employee.setName(emp.getName());
            employee.setEmail(emp.getEmail());
            employee.setAddress(emp.getAddress());
            employee.setSalary(emp.getSalary());
            employee.setGender(emp.getGender());
            employee.setPhoneNumber(emp.getPhoneNumber());
            employee.setProfilePic(filepath);

            Department department = departmentRepo.findById(emp.getDepartment().getDeptid()).orElseThrow(()-> new DepartmentNotFound("Department not found"));
            employee.setDepartment(department);
            employeeRepo.save(employee);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true , "Employee Updated Successfully",employee));
    }

    public ResponseEntity<ApiResponse<Boolean>> delete(int id) throws Exception {
            Employee employee = employeeRepo.findById(id).get();

            if (employee == null) {
                throw new UserDatailsNotFoundException("Employee Not Found");
            }
            employeeRepo.delete(employee);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Employee Deleted", true));
    }
}
