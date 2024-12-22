package com.systems.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.systems.backend.mapper.DocumentMapper;
import com.systems.backend.model.Category;
import com.systems.backend.model.Document;
import com.systems.backend.requests.CreateCategoryRequest;
import com.systems.backend.requests.PaginationRequest;
import com.systems.backend.responses.DocumentResponse;
import com.systems.backend.service.CategoryService;
import com.systems.backend.service.DocumentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentMapper documentMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Category createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        return categoryService.createCategory(createCategoryRequest);
    }


    @GetMapping("{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Category getCategory(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }


    @RequestMapping(value="{categoryId}/update", method ={RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category Category) {
        return categoryService.updateCategory(categoryId, Category);
    }
    
    @DeleteMapping("{categoryId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping("{categoryId}/documents")
    @ResponseStatus(HttpStatus.OK)
    public Page<DocumentResponse> getDocumentsByCategory(
            @PathVariable(name ="categoryId") Long categoryId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "9") int size,
            @RequestParam(name = "sortBy", defaultValue = "views") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "desc") String sortDirection,
            @RequestParam(name = "status", defaultValue = "1") Short status) {

        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Category category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found");
        }
        Page<Document> documentPage = documentService.getDocumentsByStatusAndCategory(status, category, pageable);

        return documentMapper.toDTOPage(documentPage);
    }
}
