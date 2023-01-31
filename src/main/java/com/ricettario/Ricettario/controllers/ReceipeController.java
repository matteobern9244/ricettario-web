package com.ricettario.Ricettario.controllers;

import com.ricettario.Ricettario.model.dto.ReceipeDTO;
import com.ricettario.Ricettario.model.entities.Receipe;
import com.ricettario.Ricettario.services.Mappers;
import com.ricettario.Ricettario.services.ReceipeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/receipes")
public class ReceipeController {
    @Autowired
    private ReceipeServices receipeServices;

    @Autowired
    private Mappers mappers;

    @GetMapping
    @ResponseBody
    public List<ReceipeDTO> getAllReceipes() {
        return receipeServices.getAllReceipes().stream()
                .map(mappers::convertToReceipeDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ReceipeDTO getReceipeById(@PathVariable("id") Long id) {
        return mappers.convertToReceipeDTO(receipeServices.getReceipeById(id));
    }

    @PostMapping
    public ReceipeDTO addNewReceipe(@RequestBody ReceipeDTO receipeForm) {
        Receipe newReceipe = receipeServices.addNewReceipe(mappers.convertToReceipeEntity(receipeForm));
        return mappers.convertToReceipeDTO(newReceipe);
    }

    @PutMapping("/{id}")
    public ReceipeDTO updateReceipe(@PathVariable("id") Long id, @RequestBody ReceipeDTO updatedReceipe) {
        Receipe receipe = receipeServices.updateReceipe(id, mappers.convertToReceipeEntity(updatedReceipe));
        return mappers.convertToReceipeDTO(receipe);
    }

    @DeleteMapping("/{id}")
    public void deleteReceipeById(@PathVariable("id") Long id) { receipeServices.deleteReceipeById(id); }

    @PostMapping("/multiple-delete")
    public void deleteMultipleReceipesByIds(@RequestBody Long[] ids) {
        receipeServices.deleteMultipleReceipes(Arrays.asList(ids));
    }
}
