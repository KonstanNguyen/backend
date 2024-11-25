package com.systems.backend.service.impl;

import com.systems.backend.repository.DocumentRepository;
import com.systems.backend.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
}
