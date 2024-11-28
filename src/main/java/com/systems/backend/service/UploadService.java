package com.systems.backend.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

@Service
public interface UploadService {
    String processFile(MultipartFile file) throws Exception;
}
