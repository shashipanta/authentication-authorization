package com.auth.authentication.controller;

import com.auth.authentication.dto.FileUploadMessage;
import com.auth.authentication.dto.request.FileUploadRequest;
import com.auth.authentication.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file-upload")
public class FileUploadController {


    private final FileService fileService;

    public FileUploadController( FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<FileUploadMessage> uploadFile(@RequestParam("file") MultipartFile file, @ModelAttribute FileUploadRequest fileUploadRequest){
        // string into json
        fileUploadRequest.setMultipartFile(file);
        fileService.uploadFile(fileUploadRequest);
        return ResponseEntity.ok(new FileUploadMessage("File uploaded successfully"));
    }
}
