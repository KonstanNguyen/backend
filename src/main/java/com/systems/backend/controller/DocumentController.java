package com.systems.backend.controller;

import com.systems.backend.model.Document;
import com.systems.backend.requests.CreateDocumentRequest;
import com.systems.backend.requests.PaginationRequest;
import com.systems.backend.service.DocumentService;
import com.systems.backend.service.UploadService;
import com.systems.backend.utils.UploadResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @Autowired
    private UploadService uploadService;
    
    // @GetMapping
    // @ResponseStatus(HttpStatus.OK)
    // @ResponseBody
    // public Page<Document> getAllDocuments(@RequestBody(required = false) PaginationRequest pageRequest) {
        
    //     int page = pageRequest.getPage() >= 0 ? pageRequest.getPage() : 0;
    //     int size = pageRequest.getSize() >= 1 ? pageRequest.getSize() : 3;
    //     String sortBy = pageRequest.getSortBy() != null ? pageRequest.getSortBy() : "id";
    //     String sortDir = pageRequest.getSortDirection() != null ? pageRequest.getSortDirection() : "asc";
        
    //     Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

    //     Pageable pageable = PageRequest.of(page, size, sort);

    //     return documentService.getAllDocuments(pageable);
    // }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Page<Document> getAllDocuments(
            @RequestParam(required = false) int page, 
            @RequestParam(required = false) int size, 
            @RequestParam(required = false) String sortBy, 
            @RequestParam(required = false) String sortDirection) {
        
        // Kiểm tra các giá trị đầu vào và khởi tạo các giá trị mặc định
        page = page >= 0 ? page : 0;  // đảm bảo page không âm
        size = size >= 1 ? size : 5;  // đảm bảo size không nhỏ hơn 1

        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        // Trả về dữ liệu phân trang từ service
        return documentService.getAllDocuments(pageable);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Document createDocument(@RequestBody CreateDocumentRequest createDocumentRequest, 
    @RequestParam("file") MultipartFile file) throws Exception{
        // return documentService.createDocument(createDocumentRequest);
        
        UploadResult uploadResult = uploadService.processFile(file);
        createDocumentRequest.setContent(uploadResult.getOriginalFilePath());
        createDocumentRequest.setThumbnail(uploadResult.getThumbnailFilePath());
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
