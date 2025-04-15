package com.EmployeeManagement.EmployeeManagementSystem.Controller;

import com.EmployeeManagement.EmployeeManagementSystem.Service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metadata")
@CrossOrigin(origins = "http://localhost:4200")
public class MetaDataController {

    @Autowired
    MetadataService metadataService;

    @GetMapping("/getEmployeeCount")
    public int getEmployeeCount() throws Exception {
        return metadataService.GetEmployeeCount();
    }

    @GetMapping("/getDepartmentCount")
    public int getDepartmentCount() throws Exception {
        return metadataService.GetDepartmentCount();
    }
}
