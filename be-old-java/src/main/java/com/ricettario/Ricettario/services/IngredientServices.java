package com.ricettario.Ricettario.services;

import com.ricettario.Ricettario.model.entities.Ingredient;
import com.ricettario.Ricettario.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientServices {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return new ArrayList<>(ingredientRepository.findAll());
    }

    public Ingredient getIngredientById(Long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isPresent()) {
            return ingredient.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'ingrediente non esiste.");
        }
    }

    public Ingredient addNewIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, Ingredient updatedIngredient) {
        Optional<Ingredient> targetIngredient = ingredientRepository.findById(id);
        if (targetIngredient.isPresent()) {
            updatedIngredient.setId(id);
            return ingredientRepository.save(updatedIngredient);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'ingrediente non esiste.");
        }
    }

    public void deleteIngredientById(Long id) { ingredientRepository.deleteById(id); }

    @Transactional
    public void deleteMultipleIngredients(List<Long> ids) {
        ingredientRepository.deleteIngredientsByIds(ids);
    }
}
