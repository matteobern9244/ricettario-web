package com.ricettario.Ricettario.services;

import com.ricettario.Ricettario.model.entities.Category;
import com.ricettario.Ricettario.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServices {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return new ArrayList<>(categoryRepository.findAll());
    }

    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La categoria non esiste.");
        }
    }

    public Category addNewCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Optional<Category> targetCategory = categoryRepository.findById(id);
        if (targetCategory.isPresent()) {
            updatedCategory.setId(id);
            return categoryRepository.save(updatedCategory);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La categoria non esiste.");
        }
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Transactional
    public void deleteMultipleCategories(List<Long> ids) {
        categoryRepository.deleteCategoriesByIds(ids);
    }
}
