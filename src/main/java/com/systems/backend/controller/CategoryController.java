package com.systems.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.systems.backend.model.Category;
import com.systems.backend.service.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(category));
    }


    @GetMapping("categoryId")
    public ResponseEntity<Category> getCategory(@PathVariable(name="categoryId") Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }


    @RequestMapping(value="{categoryId/update}", method ={RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<Category> updateCategory(@PathVariable(name = "categoryId") Long categoryId, @RequestBody Category Category) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, Category));
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{categoryId}/delete")
    public void deleteCategory(@PathVariable(name = "categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }


}
