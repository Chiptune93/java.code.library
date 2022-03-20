package com.file.example.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.file.example.ifc.FileStorageService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

@Service
public class FileUploadService implements FileStorageService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Override
    public void init() {

    }

    @Override
    public void save(MultipartRequest req) {
        MultipartFile file = req.getFile("singleFile");
        try {
            if (file.isEmpty()) {
                throw new Exception("ERROR : file is empty");
            }
            Path root = Paths.get(uploadPath);
            System.out.println("＃＃＃＃＃＃＃＃＃＃＃ [LOG] : " + root + "＃＃＃＃＃＃＃＃＃＃＃");
            System.out.println("＃＃＃＃＃＃＃＃＃＃＃ [LOG] : " + uploadPath + "＃＃＃＃＃＃＃＃＃＃＃");
            if (!Files.exists(root)) {
                try {
                    Files.createDirectories(Paths.get(uploadPath));
                } catch (Exception e) {
                    throw new Exception("ERROR : can't makr dir");
                }
            }
            try {
                InputStream is = file.getInputStream();
                Files.copy(is, root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                throw new Exception("ERROR : can't makr dir");
            }
        } catch (Exception e) {
            throw new RuntimeException("ERROR : can't save file !");
        }
    }

}
