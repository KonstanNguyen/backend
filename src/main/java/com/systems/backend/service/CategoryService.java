package com.systems.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.systems.backend.model.Category;

@Service
public interface CategoryService {
    Category getCategoryById(Long id);
    List<Category> getCategoryByName(String name);
    List<Category> getAllCategory();
    Category createCategory(Category category);
    void deleteCategory(Long id);
    Category updateCategory(Long id, Category category);
}
