package com.auth.authentication.service;

import com.auth.authentication.dto.request.StudentRequest;
import com.auth.authentication.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentRequest studentRequest);

    List<StudentResponse> getAllStudents();
}
