package com.systems.backend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.systems.backend.model.Account;
import com.systems.backend.model.Document;
import com.systems.backend.model.HistoryDownload;
import com.systems.backend.model.HistoryDownload.HistoryDownloadId;
import com.systems.backend.repository.HistoryDownloadRepository;
import com.systems.backend.responses.HistoryDownloadResponse;
import com.systems.backend.service.AccountService;
import com.systems.backend.service.DocumentService;
import com.systems.backend.service.HistoryDownloadService;
import com.systems.backend.mapper.HistoryDownloadMapper;

@RestController
@CrossOrigin
@RequestMapping("/api/history-downloads")
public class HistoryDownloadController {
    @Autowired
    private HistoryDownloadRepository historyDownloadRepository;
    
    @Autowired
    private HistoryDownloadService historyDownloadService;

    @Autowired
    private HistoryDownloadMapper historyDownloadMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DocumentService documentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<HistoryDownloadResponse> getAllHistoryDownloads (
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "9") int size,
            @RequestParam(name = "sortBy", defaultValue = "date") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "desc") String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<HistoryDownload> historyDownloadPage = historyDownloadService.getAllHistoryDownloads(pageable);
        return historyDownloadMapper.toDTOPage(historyDownloadPage);
    }

    @PostMapping
    public ResponseEntity<String> saveDownloadHistory(
            @RequestParam("accountId") Long accountId,
            @RequestParam("documentId") Long documentId) {
        try {
            Account account = accountService.getAccountById(accountId);
            Document document = documentService.getDocumentById(documentId);

            HistoryDownloadId historyDownloadId = new HistoryDownloadId();
            historyDownloadId.setAccount(account);
            historyDownloadId.setDocument(document);

            HistoryDownload historyDownload = new HistoryDownload();
            historyDownload.setHistoryDownloadId(historyDownloadId);
            historyDownload.setDate(LocalDateTime.now());

            historyDownloadRepository.save(historyDownload);

            return ResponseEntity.ok("Lịch sử tải xuống đã được lưu.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi lưu lịch sử tải xuống: " + e.getMessage());
        }
    }
}
