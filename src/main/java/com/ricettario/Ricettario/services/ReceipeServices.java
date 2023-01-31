package com.ricettario.Ricettario.services;

import com.ricettario.Ricettario.model.entities.Receipe;
import com.ricettario.Ricettario.repositories.ReceipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceipeServices {
    @Autowired
    private ReceipeRepository receipeRepository;

    public List<Receipe> getAllReceipes() {
        return new ArrayList<>(receipeRepository.findAll());
    }

    public Receipe getReceipeById(Long id) {
        Optional<Receipe> receipe = receipeRepository.findById(id);
        if (receipe.isPresent()) {
            return receipe.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La ricetta non esiste.");
        }
    }

    public Receipe addNewReceipe(Receipe receipe) {
        return receipeRepository.save(receipe);
    }

    public Receipe updateReceipe(Long id, Receipe updatedReceipe) {
        Optional<Receipe> targetReceipe = receipeRepository.findById(id);
        if (targetReceipe.isPresent()) {
            updatedReceipe.setId(id);
            return receipeRepository.save(updatedReceipe);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La ricetta non esiste.");
        }
    }

    public void deleteReceipeById(Long id) {
        receipeRepository.deleteById(id);
    }

    @Transactional
    public void deleteMultipleReceipes(List<Long> ids) {
        receipeRepository.deleteReceipesByIds(ids);
    }

}
