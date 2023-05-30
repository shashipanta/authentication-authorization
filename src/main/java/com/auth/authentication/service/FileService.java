package com.auth.authentication.service;

import com.auth.authentication.dto.request.FileUploadRequest;
import com.auth.authentication.dto.response.FileUploadResponse;

public interface FileService {
    
    FileUploadResponse uploadFile(FileUploadRequest fileUploadRequest);
    
    FileUploadResponse generateVideoFormat(String something);
    
    
}
