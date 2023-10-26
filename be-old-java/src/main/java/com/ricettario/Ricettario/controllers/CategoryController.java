package com.ricettario.Ricettario.controllers;

import com.ricettario.Ricettario.model.dto.CategoryDTO;
import com.ricettario.Ricettario.model.entities.Category;
import com.ricettario.Ricettario.services.CategoryServices;
import com.ricettario.Ricettario.services.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    @Autowired
    private Mappers mappers;

    @Autowired
    private CategoryServices categoryServices;

    @GetMapping
    @ResponseBody
    public List<CategoryDTO> getAllCategories() {
        return categoryServices.getAllCategories().stream()
                .map(mappers::convertToCategoryDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public CategoryDTO getCategoryById(@PathVariable("id") Long id) {
        return mappers.convertToCategoryDTO(categoryServices.getCategoryById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public CategoryDTO addNewCategory(@RequestBody CategoryDTO categoryForm) {
        Category newCategory = categoryServices.addNewCategory(mappers.convertToCategoryEntity(categoryForm));
        return mappers.convertToCategoryDTO(newCategory);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public CategoryDTO updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDTO updatedCategory) {
        Category category = categoryServices.updateCategory(id, mappers.convertToCategoryEntity(updatedCategory));
        return mappers.convertToCategoryDTO(category);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public void deleteCategoryById(@PathVariable("id") Long id) { categoryServices.deleteCategoryById(id); }

    @PostMapping("/multiple-delete")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public void deleteMultipleCategoriesByIds(@RequestBody Long[] ids) {
        categoryServices.deleteMultipleCategories(Arrays.asList(ids));
    }
}
