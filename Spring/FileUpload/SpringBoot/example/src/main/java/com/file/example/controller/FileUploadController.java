package com.file.example.controller;

import com.file.example.service.FileUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartRequest;

@RestController
public class FileUploadController {

    @Autowired
    FileUploadService fsvc;

    @PostMapping("/upload.do")
    public void upload(MultipartRequest req) {
        System.out.println("＃＃＃＃＃＃＃＃＃＃＃ [LOG] : " + req + "＃＃＃＃＃＃＃＃＃＃＃");
        fsvc.save(req);
    }
}
