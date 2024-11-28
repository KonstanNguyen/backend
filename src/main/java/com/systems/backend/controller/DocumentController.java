package com.systems.backend.controller;

import com.systems.backend.model.Category;
import com.systems.backend.model.Document;
import com.systems.backend.service.DocumentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.createDocument(document));
    }

    @GetMapping("{documentId}")
    public ResponseEntity<Document> getDocument(@PathVariable(name="documentId") Long documentId) {
        return ResponseEntity.ok(documentService.getDocumentById(documentId));
    }

    @RequestMapping(value = "{documentId}/update", method={RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<Document> updateDocument(@PathVariable(name = "documentId") Long documentId, @RequestBody Document document) {
        return ResponseEntity.ok(documentService.updateDocument(documentId, document));
    }
    

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{documentId}/delete")
    public void deleteDocument(@PathVariable(name = "documentId") Long documentId) {
        documentService.deleteDocument(documentId);
    }
    
}
