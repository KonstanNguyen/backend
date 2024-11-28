package com.systems.backend.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.systems.backend.model.HistoryDownload;

@Service
public interface HistoryDownloadService {
    
    List<HistoryDownload> getAllHistoryDownloads();

    List<HistoryDownload> getHistoryByAccountId(Long accountId);

    List<HistoryDownload> getHistoryByDocumentId(Long documentId);
}
