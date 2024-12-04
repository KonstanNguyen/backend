package com.systems.backend.service.impl;

import com.systems.backend.model.HistoryDownload;
import com.systems.backend.repository.HistoryDownloadRepository;
import com.systems.backend.service.HistoryDownloadService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryDownloadServiceImpl implements HistoryDownloadService {
    @Autowired
    private HistoryDownloadRepository historyDownloadRepository;

    @Override
    public List<HistoryDownload> getAllHistoryDownloads() {
        return historyDownloadRepository.findAll();
    }

    @Override
    public List<HistoryDownload> getHistoryByAccountId(Long accountId) {
        return historyDownloadRepository.findByAccountId(accountId);
    }

    @Override
    public List<HistoryDownload> getHistoryByDocumentId(Long documentId) {
        return historyDownloadRepository.findByDocumentId(documentId);
    }
}
