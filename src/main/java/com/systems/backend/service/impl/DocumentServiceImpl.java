package com.systems.backend.service.impl;

import com.systems.backend.model.Category;
import com.systems.backend.model.DocUser;
import com.systems.backend.model.Document;
import com.systems.backend.repository.DocumentRepository;
import com.systems.backend.request.CreateDocumentRequest;
import com.systems.backend.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Document getDocumentById(Long id) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        return documentOptional.orElse(null);
    }

    @Override
    public List<Document> gettDocumentByCategory(Category category) {
        return documentRepository.findByCategory(category);
    }

    @Override
    public List<Document> getDocumentsByAuthor(DocUser author) {
        return documentRepository.findByAuthor(author);
    }

    @Override
    public List<Document> getDocumentsByStatus(Short status) {
        return documentRepository.findByStatus(status);
    }

    @Override
    public List<Document> getDocumentsByCreateAt(LocalDateTime time) {
        return documentRepository.findByCreateAt(time);
    }

    @Override
    public List<Document> searchDocuments(String keywords) {
        return documentRepository.findByTitleContaining(keywords);
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public Document createDocument(CreateDocumentRequest createDocumentRequest) {
        if (documentRepository.existsByTitle(createDocumentRequest.getTitle())) {
            throw new RuntimeException("This document has already existed");
        }

        Document document = Document.builder()
                .title(createDocumentRequest.getTitle())
                .author(createDocumentRequest.getAuthor())
                .thumbnail(createDocumentRequest.getThumbnail())
                .content(createDocumentRequest.getContent())
                .build();
        return documentRepository.save(document);
    }

    @Override
    public void deleteDocument(Long id) {
        Document checkDocument = getDocumentById(id);
        if (checkDocument == null) {
            throw new RuntimeException("Document is not found!");
        }
        documentRepository.delete(checkDocument);
    }
    
    @Override
    public Document updateDocument(Long id, Document document) {
        Document updatedDocument = getDocumentById(id);
        if (updatedDocument == null) {
            throw new RuntimeException("This document is not found");
        }
        updatedDocument.setAuthor(document.getAuthor());
        updatedDocument.setCategory(document.getCategory());
        updatedDocument.setContent(document.getContent());
        updatedDocument.setCreateAt(document.getCreateAt());
        updatedDocument.setStatus(document.getStatus());
        updatedDocument.setThumbnail(document.getThumbnail());
        updatedDocument.setTitle(document.getTitle());
        updatedDocument.setUpdateAt(document.getUpdateAt());

        return documentRepository.save(updatedDocument);
        
    }
}
