package com.auth.authentication.controller;


import com.auth.authentication.dto.request.StudentRequest;
import com.auth.authentication.dto.response.StudentResponse;
import com.auth.authentication.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping(value = "/")
    public StudentResponse registerStudent(
            @ModelAttribute StudentRequest studentRequest,
            @RequestParam("file") MultipartFile multipartFile
    ) {
        studentRequest.setProfile(multipartFile);
        return studentService.createStudent(studentRequest);
    }

    @GetMapping("/")
    public List<StudentResponse> getRegisteredStudents() {
        return studentService.getAllStudents();
    }


}
