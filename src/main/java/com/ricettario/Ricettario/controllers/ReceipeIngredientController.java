package com.ricettario.Ricettario.controllers;

import com.ricettario.Ricettario.model.dto.ReceipeIngredientDTO;
import com.ricettario.Ricettario.model.entities.ReceipeIngredient;
import com.ricettario.Ricettario.services.Mappers;
import com.ricettario.Ricettario.services.ReceipeIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/receipeIngredient")
public class ReceipeIngredientController {
    @Autowired
    private Mappers mappers;

    @Autowired
    private ReceipeIngredientService receipeIngredientService;

    @GetMapping
    @ResponseBody
    public List<ReceipeIngredientDTO> getAllReceipesIngredients() {
        return receipeIngredientService.getAllReceipesIngredients().stream()
                .map(mappers::convertToReceipeIngredientDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ReceipeIngredientDTO getReceipeIngredient(@PathVariable("id") Long id) {
        return mappers.convertToReceipeIngredientDTO(receipeIngredientService.getReceipeIngredientById(id));
    }

    @PostMapping
    public ReceipeIngredientDTO addNewReceipeIngredient(@RequestBody ReceipeIngredientDTO receipeIngredientForm) {
        ReceipeIngredient newReceipeIngredient = receipeIngredientService.addNewReceipeIngredient(mappers.convertToReceipeIngredientEntity(receipeIngredientForm));
        return mappers.convertToReceipeIngredientDTO(newReceipeIngredient);
    }

    @PutMapping("/{id}")
    public ReceipeIngredientDTO updateReceipeIngredient(@PathVariable("id") Long id, @RequestBody ReceipeIngredientDTO updatedReceipeIngredient) {
        ReceipeIngredient receipeIngredient = receipeIngredientService.updateReceipeIngredient(id, mappers.convertToReceipeIngredientEntity(updatedReceipeIngredient));
        return mappers.convertToReceipeIngredientDTO(receipeIngredient);
    }

    @DeleteMapping("/{id}")
    public void deleteReceipeIngredientById(@PathVariable("id") Long id) { receipeIngredientService.deleteReceipeIngredientById(id); }

    @PostMapping("/multiple-delete")
    public void deleteMultipleReceipeIngredientsByIds(@RequestBody Long[] ids) {
        receipeIngredientService.deleteMultipleReceipesIngredients(Arrays.asList(ids));
    }
}
