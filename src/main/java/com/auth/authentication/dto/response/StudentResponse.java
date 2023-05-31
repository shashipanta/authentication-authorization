package com.auth.authentication.dto.response;


import com.auth.authentication.entity.Student;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {

    private String name;
    private String phoneNumber;
    private String imagePath;

    private String message;


    public static StudentResponse toStudentResponse(Student student, String message) {
        return StudentResponse.builder()
                .name(student.getName())
                .phoneNumber(student.getPhoneNumber())
                .imagePath(student.getProfileImgPath())
                .message(message)
                .build();
    }
}
