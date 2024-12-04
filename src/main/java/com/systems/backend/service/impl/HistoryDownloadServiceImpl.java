package com.systems.backend.service.impl;

import com.systems.backend.model.Account;
import com.systems.backend.model.Document;
import com.systems.backend.model.HistoryDownload;
import com.systems.backend.repository.AccountRepository;
import com.systems.backend.repository.DocumentRepository;
import com.systems.backend.repository.HistoryDownloadRepository;
import com.systems.backend.service.HistoryDownloadService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.print.Doc;

@Service
public class HistoryDownloadServiceImpl implements HistoryDownloadService {
    @Autowired
    private HistoryDownloadRepository historyDownloadRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public List<HistoryDownload> getAllHistoryDownloads() {
        return historyDownloadRepository.findAll();
    }

    @Override
    public List<HistoryDownload> getHistoryByAccountId(String username) {
        Account account = accountRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("Account " + username + " not found")
        );

        return historyDownloadRepository.findByAccountId(account.getId());
    }

    @Override
    public List<HistoryDownload> getHistoryByDocumentId(Long documentId) {
        Document document = documentRepository.findById(documentId).orElseThrow(() ->
                new ResourceNotFoundException("Document " + documentId + " not found")
        );

        return historyDownloadRepository.findByDocumentId(documentId);
    }
}
