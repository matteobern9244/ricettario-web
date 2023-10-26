package com.ricettario.Ricettario.services;

import com.ricettario.Ricettario.model.entities.Difficult;
import com.ricettario.Ricettario.repositories.DifficultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DifficultServices {
    @Autowired
    private DifficultRepository difficultRepository;

    public List<Difficult> getAllDifficult() {
        return new ArrayList<>(difficultRepository.findAll());
    }

    public Difficult getDifficultById(Long id) {
        Optional<Difficult> difficult = difficultRepository.findById(id);
        if (difficult.isPresent()) {
            return difficult.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La difficoltà non esiste.");
        }
    }

    public Difficult addNewDifficult(Difficult difficult) {
        return difficultRepository.save(difficult);
    }

    public Difficult updateDifficult(Long id, Difficult updatedDifficult) {
        Optional<Difficult> targetDifficult = difficultRepository.findById(id);
        if (targetDifficult.isPresent()) {
            updatedDifficult.setId(id);
            return difficultRepository.save(updatedDifficult);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La difficoltà non esiste.");
        }
    }

    public void deleteDifficultById(Long id) {
        difficultRepository.deleteById(id);
    }

    @Transactional
    public void deleteMultipleDifficults(List<Long> ids) {
        difficultRepository.deleteDifficultsByIds(ids);
    }
}
