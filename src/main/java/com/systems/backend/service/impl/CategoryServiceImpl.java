package com.systems.backend.service.impl;

import com.systems.backend.repository.CategoryRepository;
import com.systems.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
}
