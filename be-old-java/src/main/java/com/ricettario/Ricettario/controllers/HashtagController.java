package com.ricettario.Ricettario.controllers;

import com.ricettario.Ricettario.model.dto.HashtagDTO;
import com.ricettario.Ricettario.model.entities.Hashtag;
import com.ricettario.Ricettario.services.HashtagServices;

import com.ricettario.Ricettario.services.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/hashtags")
public class HashtagController {
    @Autowired
    private Mappers mappers;

    @Autowired
    private HashtagServices hashtagServices;

    @GetMapping
    @ResponseBody
    public List<HashtagDTO> getAllHashtags() {
        return hashtagServices.getAllHashtag().stream()
                .map(mappers::convertToHashtagDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public HashtagDTO getHashtagById(@PathVariable("id") Long id) {
        return mappers.convertToHashtagDTO(hashtagServices.getHashtagById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public HashtagDTO addNewHashtag(@RequestBody HashtagDTO hashtagForm) {
        Hashtag newHashtag = hashtagServices.addNewHashtag(mappers.convertToHashtagEntity(hashtagForm));
        return mappers.convertToHashtagDTO(newHashtag);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public HashtagDTO updateHashtag(@PathVariable("id") Long id, @RequestBody HashtagDTO updatedHashtag) {
        Hashtag hashtag = hashtagServices.updateHashtag(id, mappers.convertToHashtagEntity(updatedHashtag));
        return mappers.convertToHashtagDTO(hashtag);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public void deleteHashtagById(@PathVariable("id") Long id) { hashtagServices.deleteHashtagById(id); }

    @PostMapping("/multiple-delete")
    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    public void deleteMultipleHashtagsByIds(@RequestBody Long[] ids) {
        hashtagServices.deleteMultipleHashtags(Arrays.asList(ids));
    }
}
