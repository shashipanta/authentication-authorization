package com.auth.authentication.dto;

import lombok.Data;

@Data
public class FileUploadMessage {

    private String fileName;
    private String message;
    private String uploadedAt;

    public FileUploadMessage(String message){
        this.message = message;
    }

}
