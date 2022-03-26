package com.file.example.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository {
    List<HashMap<String, String>> fileList(HashMap<String, String> parameterMap);

    int insertFile(HashMap<String, String> parameterMap);

    int deleteFile(HashMap<String, String> parameterMap);
}