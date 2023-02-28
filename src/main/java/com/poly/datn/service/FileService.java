package com.poly.datn.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    public Object upload(MultipartFile multipartFile);
    public Object download(String fileName) throws IOException;
    public boolean delete(String fileName) throws IOException;

    public List<Object> uploadMultiFiles(List<MultipartFile> multipartFiles);
}
