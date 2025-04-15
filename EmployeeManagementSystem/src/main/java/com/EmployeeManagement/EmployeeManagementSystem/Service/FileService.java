package com.EmployeeManagement.EmployeeManagementSystem.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;



@Service
public class FileService {

    public String uploadFile( String path , MultipartFile file) throws Exception {

        String fileName = file.getOriginalFilename();

        if (fileName == null || fileName.isEmpty()) {
            throw new Exception("Invalid file name");
        }

        String filepath = path + File.separator + fileName;
        System.out.println("File path: " + path);

        File directory = new File(path);
        System.out.println("Directory" + directory);

        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            System.out.println("Directory created: " + created + " with name: " + directory.getName());
            if (!created) {
                throw new Exception("Failed to create directory: " + path);
            }
        }

        if (!directory.canWrite()) {
            throw new Exception("Cannot write to directory: " + path);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, Paths.get(filepath), StandardCopyOption.REPLACE_EXISTING);
        }

        return fileName;
    }

    public InputStream  getProfileFile(String path,String fileName) throws Exception {
        String filePath = path + File.separator + fileName;
        System.out.println("Received file: " + filePath);
        return new FileInputStream(filePath);
    }
}


