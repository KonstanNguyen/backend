package com.systems.backend.controller;

import com.systems.backend.model.Document;
import com.systems.backend.requests.CreateDocumentRequest;
import com.systems.backend.service.DocumentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Page<Document> getAllDocuments(@RequestBody(required = false) PageRequest pageRequest) {
        return documentService.getAllDocuments(pageRequest);
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
    public Document getDocument(@PathVariable(name = "documentId") Long documentId) {
        return documentService.getDocumentById(documentId);
    }

    @RequestMapping(value = "{documentId}/update", method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
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
