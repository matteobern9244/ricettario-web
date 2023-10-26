package com.ricettario.Ricettario.controllers;

import com.ricettario.Ricettario.model.dto.DifficultDTO;
import com.ricettario.Ricettario.model.entities.Difficult;
import com.ricettario.Ricettario.services.DifficultServices;
import com.ricettario.Ricettario.services.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/difficults")
public class DifficultController {
    @Autowired
    private Mappers mappers;

    @Autowired
    private DifficultServices difficultServices;

    @GetMapping
    @ResponseBody
    public List<DifficultDTO> getAllDifficults() {
        return difficultServices.getAllDifficult().stream()
                .map(mappers::convertToDifficultDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public DifficultDTO getDifficultById(@PathVariable("id") Long id) {
        return mappers.convertToDifficultDTO(difficultServices.getDifficultById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public DifficultDTO addNewDifficult(@RequestBody DifficultDTO difficultForm) {
        Difficult newDifficult = difficultServices.addNewDifficult(mappers.convertToDifficultEntity(difficultForm));
        return mappers.convertToDifficultDTO(newDifficult);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public DifficultDTO updateDifficult(@PathVariable("id") Long id, @RequestBody DifficultDTO updatedDifficult) {
        Difficult difficult = difficultServices.updateDifficult(id, mappers.convertToDifficultEntity(updatedDifficult));
        return mappers.convertToDifficultDTO(difficult);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public void deleteDifficultById(@PathVariable("id") Long id) { difficultServices.deleteDifficultById(id); }

    @PostMapping("/multiple-delete")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public void deleteMultipleDifficultsByIds(@RequestBody Long[] ids) {
        difficultServices.deleteMultipleDifficults(Arrays.asList(ids));
    }
}
