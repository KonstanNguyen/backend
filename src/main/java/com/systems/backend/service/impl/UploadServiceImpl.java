package com.systems.backend.service.impl;

import com.systems.backend.service.UploadService;
import com.systems.backend.utils.UploadResult;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public UploadResult processFile(MultipartFile file) throws Exception {
        String uploadDir = "uploads/";
        File folder = new File(uploadDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String filePath = uploadDir + originalFilename;
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(file.getBytes());
        }

        File pdfFile = new File(filePath);
        PDDocument document = PDDocument.load(pdfFile);
        PDFRenderer renderer = new PDFRenderer(document);

        BufferedImage image = renderer.renderImageWithDPI(0, 300);
        String thumbnailPath = uploadDir + "thumbnail_" + originalFilename + ".png";
        ImageIO.write(image, "PNG", new File(thumbnailPath));

        document.close();

        return new UploadResult(filePath, thumbnailPath);
    }
}
