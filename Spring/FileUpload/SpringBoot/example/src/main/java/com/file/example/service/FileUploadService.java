package com.file.example.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

import com.file.example.ifc.FileStorageService;
import com.file.example.repository.FileUploadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

@Service
public class FileUploadService implements FileStorageService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Autowired
    FileUploadRepository rpt;

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

    @Override
    public void save2(MultipartRequest req) {
        MultipartFile file = req.getFile("singleFile2");
        try {
            if (file.isEmpty()) {
                throw new Exception("ERROR : file is empty");
            }
            Path root = Paths.get(uploadPath);
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
            // 파일정보 저장
            String fileName = file.getOriginalFilename();
            String fileSize = Long.toString(file.getSize());
            String fileType = file.getContentType();
            String filePath = uploadPath + "/" + fileName;

            HashMap<String,String> fileMap = new HashMap<String,String>();
            fileMap.put("fileName", fileName);
            fileMap.put("fileSize", fileSize);
            fileMap.put("fileType", fileType);
            fileMap.put("filePath", filePath);

            rpt.insertFile(fileMap);

        } catch (Exception e) {
            throw new RuntimeException("ERROR : can't save file !");
        }
    }

}
