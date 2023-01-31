package com.ricettario.Ricettario.services;

import com.ricettario.Ricettario.model.entities.Cost;
import com.ricettario.Ricettario.repositories.CostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CostServices {
    @Autowired
    private CostRepository costRepository;

    public List<Cost> getAllCosts() {
        return new ArrayList<>(costRepository.findAll());
    }

    public Cost getCostById(Long id) {
        Optional<Cost> cost = costRepository.findById(id);
        if (cost.isPresent()) {
            return cost.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il costo non esiste.");
        }
    }

    public Cost addNewCost(Cost cost) { return costRepository.save(cost); }

    public Cost updateCost(Long id, Cost updatedCost) {
        Optional<Cost> targetCost = costRepository.findById(id);
        if (targetCost.isPresent()) {
            updatedCost.setId(id);
            return costRepository.save(updatedCost);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il costo non esiste.");
        }
    }

    public void deleteCostById(Long id) {
        costRepository.deleteById(id);
    }

    @Transactional
    public void deleteMultipleCosts(List<Long> ids) {
        costRepository.deleteCostsByIds(ids);
    }
}
