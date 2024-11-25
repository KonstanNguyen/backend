package com.systems.backend.service.impl;

import com.systems.backend.repository.HistoryDownloadRepository;
import com.systems.backend.service.HistoryDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryDownloadServiceImpl implements HistoryDownloadService {
    @Autowired
    private HistoryDownloadRepository historyDownloadRepository;
}
