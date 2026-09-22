package com.supermart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermart.entity.Category;
import com.supermart.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) {

        Category existing = categoryRepository.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(category.getName());
            existing.setDescription(category.getDescription());
            existing.setActive(category.isActive());

            return categoryRepository.save(existing);
        }

        return null;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}