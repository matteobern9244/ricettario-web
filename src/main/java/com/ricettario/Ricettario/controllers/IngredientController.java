package com.ricettario.Ricettario.controllers;

import com.ricettario.Ricettario.model.dto.IngredientDTO;
import com.ricettario.Ricettario.model.entities.Ingredient;
import com.ricettario.Ricettario.services.IngredientServices;

import com.ricettario.Ricettario.services.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/ingredients")
public class IngredientController {
    @Autowired
    private Mappers mappers;

    @Autowired
    private IngredientServices ingredientServices;

    @GetMapping
    @ResponseBody
    public List<IngredientDTO> getAllIngredients() {
        return ingredientServices.getAllIngredients().stream()
                .map(mappers::convertToIngredientDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public IngredientDTO getIngredienById(@PathVariable("id") Long id) {
        return mappers.convertToIngredientDTO(ingredientServices.getIngredientById(id));
    }

    @PostMapping
    public IngredientDTO addNewIngredient(@RequestBody IngredientDTO ingredientForm) {
        Ingredient newIngredient = ingredientServices.addNewIngredient(mappers.convertToIngredientEntity(ingredientForm));
        return mappers.convertToIngredientDTO(newIngredient);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public IngredientDTO updateIngredient(@PathVariable("id") Long id, @RequestBody IngredientDTO updatedIngredient) {
        Ingredient ingredient = ingredientServices.updateIngredient(id, mappers.convertToIngredientEntity(updatedIngredient));
        return mappers.convertToIngredientDTO(ingredient);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public void deleteIngredientById(@PathVariable("id") Long id) { ingredientServices.deleteIngredientById(id); }

    @PostMapping("/multiple-delete")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public void deleteMultipleIngredientsByIds(@RequestBody Long[] ids) {
        ingredientServices.deleteMultipleIngredients(Arrays.asList(ids));
    }
}
