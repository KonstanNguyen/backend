package com.systems.backend.service;

import com.systems.backend.model.Category;
import com.systems.backend.model.DocUser;
import com.systems.backend.model.Document;
import com.systems.backend.requests.CreateDocumentRequest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DocumentService {
    Document getDocumentById(Long id);
    List<Document> gettDocumentByCategory(Category category);
    List<Document> getDocumentsByAuthor(DocUser author);
    List<Document> getDocumentsByStatus(Short status);
    List<Document> getDocumentsByCreateAt(LocalDateTime time);
    List<Document> searchDocuments(String keywords);
    Page<Document> getAllDocuments(Pageable pageable);
    Document createDocument(CreateDocumentRequest createDocumentRequest);
    void deleteDocument(Long id);
    Document updateDocument(Long id, Document document);
}
