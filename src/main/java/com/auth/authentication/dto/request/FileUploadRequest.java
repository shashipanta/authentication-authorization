package com.auth.authentication.dto.request;

import com.auth.authentication.entity.File;
import com.auth.authentication.utils.FileSaveUtils;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
public class FileUploadRequest {

    private MultipartFile multipartFile;
    private LocalDateTime uploadedAt;
    private LocalDateTime updatedAt;

    private String someMessage;

    public static File toFile(FileUploadRequest fileUploadRequest){
        File file = new File();
        file.setUploadedAt(LocalDateTime.now());
        file.setUpdatedAt(LocalDateTime.now());

        return file;
    }
}
