package com.ricettario.Ricettario.controllers;

import com.ricettario.Ricettario.model.dto.CostDTO;
import com.ricettario.Ricettario.model.entities.Cost;
import com.ricettario.Ricettario.services.CostServices;
import com.ricettario.Ricettario.services.Mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/costs")
public class CostController {
    @Autowired
    private Mappers mappers;

    @Autowired
    private CostServices costServices;

    @GetMapping
    @ResponseBody
    public List<CostDTO> getAllCosts() {
        return costServices.getAllCosts().stream()
                .map(mappers::convertToCostDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    @ResponseBody
    public CostDTO getCostById(@PathVariable("id") Long id) {
        return mappers.convertToCostDTO(costServices.getCostById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public CostDTO addNewCost(@RequestBody CostDTO costForm) {
        Cost newCost = costServices.addNewCost(mappers.convertToCostEntity(costForm));
        return mappers.convertToCostDTO(newCost);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public CostDTO updateCost(@PathVariable("id") Long id, @RequestBody CostDTO updatedCost) {
        Cost cost = costServices.updateCost(id, mappers.convertToCostEntity(updatedCost));
        return mappers.convertToCostDTO(cost);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public void deleteCostById(@PathVariable("id") Long id) { costServices.deleteCostById(id); }

    @PostMapping("/multiple-delete")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public void deleteMultipleCostsByIds(@RequestBody Long[] ids) {
        costServices.deleteMultipleCosts(Arrays.asList(ids));
    }
}
