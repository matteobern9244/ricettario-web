package com.ricettario.Ricettario.controllers;

import com.ricettario.Ricettario.model.dto.ReceipeFavoritesUserDTO;
import com.ricettario.Ricettario.model.entities.ReceipeFavoritesUser;

import com.ricettario.Ricettario.services.Mappers;
import com.ricettario.Ricettario.services.ReceipeFavoritesUsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/receipesFavoritesUsers")
public class ReceipeFavoritesController {
    @Autowired
    private Mappers mappers;

    @Autowired
    private ReceipeFavoritesUsersService receipeFavoritesUsersService;

    @GetMapping
    @ResponseBody
    public List<ReceipeFavoritesUserDTO> getAllReceipesFavoritesUsers() {
        return receipeFavoritesUsersService.getAllReceipesFavoritesUser().stream()
                .map(mappers::convertToReceipeFavoritesUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ReceipeFavoritesUserDTO getReceipeFavoritesUserById(@PathVariable("id") Long id) {
        return mappers.convertToReceipeFavoritesUserDTO(receipeFavoritesUsersService.getReceipeFavoritesUserById(id));
    }

    @PostMapping
    public ReceipeFavoritesUserDTO addNewReceipeFavoritesUser(@RequestBody ReceipeFavoritesUserDTO receipeFavoritesUserForm) {
        ReceipeFavoritesUser newReceipeFavoritesUsers = receipeFavoritesUsersService.addNewReceipeFavoritesUser(mappers.convertToReceipeFavoritesUserEntity(receipeFavoritesUserForm));
        return mappers.convertToReceipeFavoritesUserDTO(newReceipeFavoritesUsers);
    }

    @PutMapping("/{id}")
    public ReceipeFavoritesUserDTO updateReceipeFavoritesUser(@PathVariable("id") Long id, @RequestBody ReceipeFavoritesUserDTO updatedReceipeFavoritesUser) {
        ReceipeFavoritesUser receipeFavoritesUser = receipeFavoritesUsersService.updateReceipeFavoritesUser(id, mappers.convertToReceipeFavoritesUserEntity(updatedReceipeFavoritesUser));
        return mappers.convertToReceipeFavoritesUserDTO(receipeFavoritesUser);
    }

    @DeleteMapping("/{id}")
    public void deleteReceipeFavoritesUserById(@PathVariable("id") Long id) { receipeFavoritesUsersService.deleteReceipeFavoritesUserById(id); }

    @PostMapping("/multiple-delete")
    public void deleteMultipleReceipesFavoritesUsersByIds(@RequestBody Long[] ids) {
        receipeFavoritesUsersService.deleteMultipleReceipesFavoritesUser(Arrays.asList(ids));
    }

}
