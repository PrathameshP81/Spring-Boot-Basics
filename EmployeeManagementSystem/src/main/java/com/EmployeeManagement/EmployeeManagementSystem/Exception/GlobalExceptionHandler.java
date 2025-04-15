package com.EmployeeManagement.EmployeeManagementSystem.Exception;

import com.EmployeeManagement.EmployeeManagementSystem.ApiResponse.ApiResponse;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomeException.class, MissingServletRequestPartException.class , FileUploadException.class , NoSuchElementException.class})
    public ResponseEntity<ApiResponse<Object>> handleExceptions(Exception e) {
        System.out.println(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, "Please Provide Valid User Details", null));
    }


    @ExceptionHandler(UserDatailsNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserDatailsNotFoundException(UserDatailsNotFoundException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, e.getMessage(), null));

    }
    @ExceptionHandler(DepartmentNotFound.class)
    public ResponseEntity<ApiResponse<Object>> handleDetailsNotFound(DepartmentNotFound e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, e.getMessage(), null));
    }
}
