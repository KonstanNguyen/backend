package com.systems.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.systems.backend.model.HistoryDownload;
import com.systems.backend.responses.HistoryDownloadResponse;

@Service
public interface HistoryDownloadService {
    
    Page<HistoryDownload> getAllHistoryDownloads(Pageable pageable);

    List<HistoryDownloadResponse> getHistoryByAccountId(Long accountId);

    List<HistoryDownloadResponse> getHistoryByDocumentId(Long documentId);
}
