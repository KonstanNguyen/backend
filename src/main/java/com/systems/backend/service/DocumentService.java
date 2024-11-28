package com.systems.backend.service;

import com.systems.backend.model.DocUser;
import com.systems.backend.model.Document;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DocumentService {
    Document getDocumentById(Long id);
    List<Document> gettDocumentByCategory(Long category);
    List<Document> getDocumentsByAuthor(DocUser author);
    List<Document> getDocumentsByStatus(Short status);
    List<Document> getDocumentsByCreateAt(LocalDateTime time);
    List<Document> searchDocuments(String keywords);
    List<Document> getAllDocuments();
    Document createDocument(Document document);
    void deleteDocument(Long id);
    Document updateDocument(Long id, Document document);
}
