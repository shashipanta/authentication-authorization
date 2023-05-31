package com.auth.authentication.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileSaveUtils {

    private static String ROOT_DIR = System.getProperty("user.home")
            + File.separator
            + "Desktop/File_upload";

    public String saveMultipartFile(MultipartFile multipartFile){

        File rootDir = new File(ROOT_DIR);

        // create directory if not exists
        if(!rootDir.exists()){
            rootDir.mkdirs();
        }

        // save file
        String originalFileName = multipartFile.getOriginalFilename();
        String filePath = ROOT_DIR + File.separator + originalFileName;


        File myFile = new File(filePath);

        // save file
        try {
            multipartFile.transferTo(myFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
}
