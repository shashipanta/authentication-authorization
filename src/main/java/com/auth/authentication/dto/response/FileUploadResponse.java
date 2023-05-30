package com.auth.authentication.dto.response;

import com.auth.authentication.entity.File;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileUploadResponse {

    private Short id;
    private String filePath;
    private String uploadedAt;
    private String updatedAt;

    public static FileUploadResponse toFileUploadResponse(File file){
        return FileUploadResponse
                .builder()
                .id(file.getId())
                .filePath(file.getFilePath())
                .uploadedAt(file.getUploadedAt().toString())
                .updatedAt(file.getUpdatedAt().toString())
                .build();
    }
}
