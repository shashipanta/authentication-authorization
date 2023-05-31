package com.auth.authentication.dto.request;

import com.auth.authentication.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    private String name;
    private String phoneNumber;
    private MultipartFile profile;

    public static Student toStudent(StudentRequest studentRequest){

        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());

        return student;
    }
}
