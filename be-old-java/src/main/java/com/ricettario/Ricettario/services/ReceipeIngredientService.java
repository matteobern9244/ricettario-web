package com.ricettario.Ricettario.services;

import com.ricettario.Ricettario.model.entities.ReceipeIngredient;
import com.ricettario.Ricettario.repositories.ReceipeIngredientRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceipeIngredientService {
    @Autowired
    private ReceipeIngredientRepositories receipeIngredientRepositories;

    public List<ReceipeIngredient> getAllReceipesIngredients() {
        return new ArrayList<>(receipeIngredientRepositories.findAll());
    }

    public ReceipeIngredient getReceipeIngredientById(Long id) {
        Optional<ReceipeIngredient> receipeIngredient = receipeIngredientRepositories.findById(id);
        if (receipeIngredient.isPresent()) {
            return receipeIngredient.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'ingrediente associato alla ricetta non esiste.");
        }
    }

    public ReceipeIngredient addNewReceipeIngredient(ReceipeIngredient receipeIngredient) {
        return receipeIngredientRepositories.save(receipeIngredient);
    }

    public ReceipeIngredient updateReceipeIngredient(Long id, ReceipeIngredient updatedReceipeIngredient) {
        Optional<ReceipeIngredient> targetReceipeIngredient = receipeIngredientRepositories.findById(id);
        if (targetReceipeIngredient.isPresent()) {
            updatedReceipeIngredient.setId(id);
            return receipeIngredientRepositories.save(updatedReceipeIngredient);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'ingrediente associato alla ricetta non esiste.");
        }
    }

    public void deleteReceipeIngredientById(Long id) { receipeIngredientRepositories.deleteById(id); }

    @Transactional
    public void deleteMultipleReceipesIngredients(List<Long> ids) {
        receipeIngredientRepositories.deleteReceipesIngredientsByIds(ids);
    }
}
