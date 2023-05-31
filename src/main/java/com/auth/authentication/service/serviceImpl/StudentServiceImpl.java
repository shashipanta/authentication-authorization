package com.auth.authentication.service.serviceImpl;

import com.auth.authentication.dto.request.StudentRequest;
import com.auth.authentication.dto.response.StudentResponse;
import com.auth.authentication.entity.Student;
import com.auth.authentication.repo.StudentRepo;
import com.auth.authentication.service.StudentService;
import com.auth.authentication.utils.FileSaveUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final FileSaveUtils fileSaveUtils;
    private final StudentRepo studentRepo;

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {

        Student student = StudentRequest.toStudent(studentRequest);

        student.setProfileImgPath(fileSaveUtils.saveMultipartFile(studentRequest.getProfile()));

        student = studentRepo.save(student);
        return StudentResponse.toStudentResponse(student, "Student created successfully");
    }

    @Override
    public List<StudentResponse> getAllStudents() {

        List<Student> studentList = studentRepo.findAll();

        return studentList.stream()
                    .map(student -> StudentResponse.toStudentResponse(student, ""))
                    .collect(Collectors.toList());
    }
}
