package com.auth.authentication.service.serviceImpl;

import com.auth.authentication.dto.CustomMessage;
import com.auth.authentication.dto.request.FileUploadRequest;
import com.auth.authentication.dto.response.FileUploadResponse;
import com.auth.authentication.entity.File;
import com.auth.authentication.repo.FileRepo;
import com.auth.authentication.service.FileService;
import com.auth.authentication.utils.FileSaveUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepo fileRepo;
    private final FileSaveUtils fileSaveUtils;
    @Override
    public FileUploadResponse uploadFile(FileUploadRequest fileUploadRequest) {

        File file = FileUploadRequest.toFile(fileUploadRequest);

        String filePath = fileSaveUtils.saveMultipartFile(fileUploadRequest.getMultipartFile());

        file.setFilePath(filePath);

        fileRepo.save(file);

        return FileUploadResponse.toFileUploadResponse(file);
    }

    @Override
    public FileUploadResponse generateVideoFormat(String something) {
        return null;
    }
}
