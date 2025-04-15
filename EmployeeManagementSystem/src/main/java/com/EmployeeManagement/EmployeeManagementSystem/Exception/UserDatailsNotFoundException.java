package com.EmployeeManagement.EmployeeManagementSystem.Exception;

public class UserDatailsNotFoundException extends RuntimeException {
    public UserDatailsNotFoundException(String pleaseProvideUserDetails) {
        super(pleaseProvideUserDetails);
    }
}
