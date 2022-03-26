package com.file.example.util;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;

import com.file.example.repository.FileUploadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    private long MAX_SIZE = 52428800;

    @Autowired
    FileUploadRepository rpt;

    /**
     * 업로드 경로 구하기
     * 
     * @param type
     * @return
     */
    public Path getUploadPath(String type) {
        // 파일은 기본적으로 날짜 기준 (yyyymmdd) 으로 폴더를 구분
        LocalDate ld = LocalDate.now();
        String date = ld.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String typeFolder = "";
        // 타입에 따라 날짜 내부에 폴더 구분
        if (type.equals("image")) {
            typeFolder = "image";
        } else if (type.equals("document")) {
            typeFolder = "document";
        } else {
            typeFolder = "";
        }
        // 업로드 경로를 조합
        uploadPath += File.separator + date + File.separator + typeFolder;
        // 조합된 경로 체크
        Path dir = Paths.get(uploadPath);
        // 해당 경로 존재하는지 체크
        if (!Files.exists(dir)) {
            try {
                // 경로가 없다면 생성
                Files.createDirectories(dir);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return dir;
    }

    /**
     * 업로드 하기
     * 
     * @param file
     * @param path
     */
    public HashMap<String, String> upload(MultipartFile file, Path path) {
        HashMap<String, String> result = new HashMap<String, String>();
        // 파일 정보
        String fileName = file.getOriginalFilename();
        System.out.println("＃＃＃＃＃＃＃＃＃＃＃ [LOG] : " + fileName + "＃＃＃＃＃＃＃＃＃＃＃");
        String fileSize = Long.toString(file.getSize());
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
        String fileType = file.getContentType();
        String filePath = "";
        // 결과 정보
        String status = "";
        String message = "";
        // 예외처리 하기

        // 1. 파일 사이즈
        if (file.getSize() > MAX_SIZE) {
            status = "fail";
            message = "file over max upload size";
        }

        // 2. 파일 확장자
        System.out.println("＃＃＃＃＃＃＃＃＃＃＃ [LOG] : " + fileType + "＃＃＃＃＃＃＃＃＃＃＃");
        // if(!Arrays.asList("").contains(fileType)) {
        // status = "fail";
        // message = "file type is not allowed";
        // }

        // 3. 저장 파일 이름 랜덤화
        String tempName = fileName.substring(0, fileName.lastIndexOf("."));
        String encFileName = Base64.getEncoder().encodeToString(tempName.getBytes());
        // 암호화된 경로로 패스 설정
        filePath = path.toString() + File.separator + encFileName + "." + fileExt;

        // 4. 파일정보 맵에 담기.
        HashMap<String, String> fileInfo = new HashMap<String, String>();

        fileInfo.put("fileName", fileName);
        fileInfo.put("encFileName", encFileName);
        fileInfo.put("fileSize", fileSize);
        fileInfo.put("fileExt", fileExt);
        fileInfo.put("fileType", fileType);
        fileInfo.put("filePath", filePath);

        System.out.println("＃＃＃＃＃＃＃＃＃＃＃ [LOG] fileInfo: " + fileInfo + "＃＃＃＃＃＃＃＃＃＃＃");
        System.out.println(
                "＃＃＃＃＃＃＃＃＃＃＃ [LOG] path.resolve(encFileName): " + path.resolve(encFileName).toString() + "＃＃＃＃＃＃＃＃＃＃＃");
        try {
            InputStream is = file.getInputStream();
            Files.copy(is, path.resolve(encFileName + "." + fileExt), StandardCopyOption.REPLACE_EXISTING);

            // 파일 저장에 성공하면 DB에 저장하기
            int seq = rpt.insertFile(fileInfo);

            status = "success";
            message = "upload complete";
        } catch (Exception e) {
            e.printStackTrace();
            status = "fail";
            message = "upload fail";
        }
        result.put("status", status);
        result.put("message", message);
        return result;
    }
}
