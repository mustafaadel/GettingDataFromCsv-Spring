package com.atomscode.uploadcsv.student;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/student")
@RequiredArgsConstructor
public class StudentController {
    @Autowired
    private final StudentService studentService;
    @PostMapping(value = "/upload-csv", consumes = "multipart/form-data")
    public ResponseEntity<Integer> uploadCsv(
            @RequestPart("file")MultipartFile file
            ) throws IOException {
        return ResponseEntity.ok(studentService.uploadStudent(file));
    }
}
