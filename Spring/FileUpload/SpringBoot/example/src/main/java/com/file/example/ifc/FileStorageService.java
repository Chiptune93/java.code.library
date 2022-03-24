package com.file.example.ifc;

import org.springframework.web.multipart.MultipartRequest;

public interface FileStorageService {
    void init();
    
    /**
     * Basic File Upload
     * jsp form --> multipartRequest
     * Server File Dir upload 
     * @param req
     */
    void save(MultipartRequest req);

    /**
     * File Upload 2
     * upload 1 + file information + insert DB
     */
    void save2(MultipartRequest req);

}
