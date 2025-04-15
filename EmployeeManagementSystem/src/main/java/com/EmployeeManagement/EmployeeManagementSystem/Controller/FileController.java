package com.EmployeeManagement.EmployeeManagementSystem.Controller;

import com.EmployeeManagement.EmployeeManagementSystem.Service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;

@Controller
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @Value("${file.upload-dir}")
    private String basePath;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file) throws Exception {

        try {

            if (file == null || file.isEmpty()) {
                return new ResponseEntity<>("File is empty or invalid", HttpStatus.BAD_REQUEST);
            }

            String fileName = fileService.uploadFile(basePath , file);

            return new ResponseEntity<>("File uploaded successfully with name: " + fileName, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return new ResponseEntity<>("Oops! Something went wrong: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{fileName}")
    public void getFile(@PathVariable  String fileName, HttpServletResponse response) throws Exception {
            InputStream resourceFile =  fileService.getProfileFile(basePath, fileName);
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            StreamUtils.copy(resourceFile, response.getOutputStream());
    }
}
