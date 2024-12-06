package com.systems.backend.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.systems.backend.utils.PdfPageExtractor;

@RestController
public class PdfController {

    @GetMapping("/pdf/page")
    public ResponseEntity<byte[]> getPdfPage(
            @RequestParam String pdfPath,
            @RequestParam int pageNumber) {
        try {
            // Log cho việc debug
            System.out.println("pdfPath: " + pdfPath);
            System.out.println("pageNumber: " + pageNumber);

            byte[] pdfPage = PdfPageExtractor.extractPage(pdfPath, pageNumber);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=page-" + pageNumber + ".pdf");
            headers.add("Content-Type", "application/pdf");

            return new ResponseEntity<>(pdfPage, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
