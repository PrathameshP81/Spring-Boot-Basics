package com.EmployeeManagement.EmployeeManagementSystem.Controller;

import com.EmployeeManagement.EmployeeManagementSystem.ApiResponse.ApiResponse;
import com.EmployeeManagement.EmployeeManagementSystem.Entity.Employee;
import com.EmployeeManagement.EmployeeManagementSystem.Exception.CustomeException;
import com.EmployeeManagement.EmployeeManagementSystem.Exception.DepartmentNotFound;
import com.EmployeeManagement.EmployeeManagementSystem.Exception.UserDatailsNotFoundException;
import com.EmployeeManagement.EmployeeManagementSystem.Service.EmployeeService;
import com.EmployeeManagement.EmployeeManagementSystem.Service.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataInput;
import java.util.List;

@RestController()
@RequestMapping("/Employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    FileService fileService;
    @PostMapping("/create")

    public ResponseEntity<ApiResponse<Employee>> createEmployee (
            @RequestPart("file") MultipartFile file,
             @RequestPart String employee
           ) throws Exception {

        if (file == null || file.isEmpty() || employee == null || employee.isEmpty()) {
            throw new CustomeException("Please Provide User Details");
        }

        Employee newEmployee = null;
        ObjectMapper mapper = new ObjectMapper();
        newEmployee = mapper.readValue(employee, Employee.class);

        return employeeService.create(newEmployee, file);
    }


    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee() throws Exception {
        return employeeService.getAll();
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<ApiResponse<Employee>> getById(@PathVariable  int id) throws Exception {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@RequestPart("file") MultipartFile file,
                                   @RequestPart String employee) throws Exception {
        Employee newEmployee = null;

        if (file == null || file.isEmpty()) {
            throw new CustomeException("Please Provide User Details");
        }

        ObjectMapper mapper = new ObjectMapper();
        newEmployee = mapper.readValue(employee, Employee.class);

        if(newEmployee == null) {
            throw new UserDatailsNotFoundException("Please Provide User Details");
        }
        return employeeService.update(file , newEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteEmployee(@PathVariable int id) throws Exception {
       return employeeService.delete(id);
    }
}
