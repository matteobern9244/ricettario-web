package com.ricettario.Ricettario.services;

import com.ricettario.Ricettario.model.entities.ReceipeFavoritesUser;
import com.ricettario.Ricettario.repositories.ReceipeFavoritesUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceipeFavoritesUsersService {
    @Autowired
    private ReceipeFavoritesUserRepository receipeFavoritesUserRepository;

    public List<ReceipeFavoritesUser> getAllReceipesFavoritesUser() {
        return new ArrayList<>(receipeFavoritesUserRepository.findAll());
    }

    public ReceipeFavoritesUser getReceipeFavoritesUserById(Long id) {
        Optional<ReceipeFavoritesUser> receipeFavoritesUser = receipeFavoritesUserRepository.findById(id);
        if (receipeFavoritesUser.isPresent()) {
            return receipeFavoritesUser.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La ricetta preferita non esiste.");
        }
    }

    public ReceipeFavoritesUser addNewReceipeFavoritesUser(ReceipeFavoritesUser receipeFavoritesUser) {
        return receipeFavoritesUserRepository.save(receipeFavoritesUser);
    }

    public ReceipeFavoritesUser updateReceipeFavoritesUser(Long id, ReceipeFavoritesUser updatedReceipeFavoritesUser) {
        Optional<ReceipeFavoritesUser> targetReceipeFavoritesUser = receipeFavoritesUserRepository.findById(id);
        if (targetReceipeFavoritesUser.isPresent()) {
            updatedReceipeFavoritesUser.setId(id);
            return receipeFavoritesUserRepository.save(updatedReceipeFavoritesUser);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La ricetta preferita non esiste.");
        }
    }

    public void deleteReceipeFavoritesUserById(Long id) {
        receipeFavoritesUserRepository.deleteById(id);
    }

    @Transactional
    public void deleteMultipleReceipesFavoritesUser(List<Long> ids) {
        receipeFavoritesUserRepository.deleteReceipesFavoritesUsersByIds(ids);
    }
}
