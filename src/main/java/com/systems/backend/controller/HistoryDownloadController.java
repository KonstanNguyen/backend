package com.systems.backend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.systems.backend.model.HistoryDownload;
import com.systems.backend.model.HistoryDownload.HistoryDownloadId;
import com.systems.backend.repository.HistoryDownloadRepository;
import com.systems.backend.service.HistoryDownloadService;

@RestController
@CrossOrigin
@RequestMapping("/api/history-downloads")
public class HistoryDownloadController {
    @Autowired
    private HistoryDownloadService historyDownloadService;

    @GetMapping
    public ResponseEntity<List<HistoryDownload>> getAllHistoryDownloads() {
        try {
            List<HistoryDownload> historyDownloads = historyDownloadService.findAll();
            return ResponseEntity.ok(historyDownloads);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<HistoryDownload>> getHistoryByAccountId(@PathVariable Long accountId) {
        try {
            List<HistoryDownload> historyDownloads = historyDownloadService.findByAccountId(accountId);
            if (historyDownloads.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.ok(historyDownloads);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/top")
    public ResponseEntity<?> getTopDownloadedDocuments(@RequestParam int limit) {
        try {
            List<Object[]> topDownloads = historyDownloadService.findTopDownloadedDocuments(limit);
            return ResponseEntity.ok(topDownloads);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving top downloads");
        }
    }
}
