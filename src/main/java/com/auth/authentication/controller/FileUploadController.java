package com.auth.authentication.controller;

import com.auth.authentication.dto.CustomMessage;
import com.auth.authentication.dto.request.FileUploadRequest;
import com.auth.authentication.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/file-upload")
public class FileUploadController {

    private final FileService fileService;

    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping()
    public ResponseEntity<CustomMessage> uploadFile(@RequestBody FileUploadRequest fileUploadRequest){
        fileService.uploadFile(fileUploadRequest);
        return ResponseEntity.ok().build();
    }
}
