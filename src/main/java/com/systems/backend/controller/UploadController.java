package com.systems.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.systems.backend.service.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            uploadService.processFile(file);

            return ResponseEntity.ok("Save file success!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error upload file: " + e.getMessage());
        }
    }
}
