package com.file.example.ifc;

import org.springframework.web.multipart.MultipartRequest;

public interface FileStorageService {
    void init();

    void save(MultipartRequest req);

}
