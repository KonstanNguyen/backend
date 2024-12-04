package com.systems.backend.controller;

import com.systems.backend.model.Document;
import com.systems.backend.requests.CreateDocumentRequest;
import com.systems.backend.service.DocumentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Document createDocument(@RequestBody CreateDocumentRequest createDocumentRequest) {
        return documentService.createDocument(createDocumentRequest);
    }

    @GetMapping("{documentId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Document getDocument(@PathVariable(name="documentId") Long documentId) {
        return documentService.getDocumentById(documentId);
    }

    @RequestMapping(value = "{documentId}/update", method={RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Document updateDocument(@PathVariable(name = "documentId") Long documentId, @RequestBody Document document) {
        return documentService.updateDocument(documentId, document);
    }
    

    @DeleteMapping("{documentId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDocument(@PathVariable(name = "documentId") Long documentId) {
        documentService.deleteDocument(documentId);
    }
    
}
