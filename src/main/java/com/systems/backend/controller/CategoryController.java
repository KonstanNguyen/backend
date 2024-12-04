package com.systems.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.systems.backend.model.Category;
import com.systems.backend.requests.CreateCategoryRequest;
import com.systems.backend.service.CategoryService;

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


    @GetMapping("categoryId")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Category getCategory(@PathVariable(name="categoryId") Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }


    @RequestMapping(value="{categoryId/update}", method ={RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Category updateCategory(@PathVariable(name = "categoryId") Long categoryId, @RequestBody Category Category) {
        return categoryService.updateCategory(categoryId, Category);
    }
    
    @DeleteMapping("{categoryId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable(name = "categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }


}
