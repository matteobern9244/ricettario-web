package com.ricettario.Ricettario.services;

import com.ricettario.Ricettario.model.entities.Hashtag;
import com.ricettario.Ricettario.repositories.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HashtagServices {
    @Autowired
    private HashtagRepository hashtagRepository;

    public List<Hashtag> getAllHashtag() {
        return new ArrayList<>(hashtagRepository.findAll());
    }

    public Hashtag getHashtagById(Long id) {
        Optional<Hashtag> hashtag = hashtagRepository.findById(id);
        if (hashtag.isPresent()) {
            return hashtag.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'hashtag non esiste.");
        }
    }

    public Hashtag addNewHashtag(Hashtag hashtag) {
        return hashtagRepository.save(hashtag);
    }

    public Hashtag updateHashtag(Long id, Hashtag updatedHashtag) {
        Optional<Hashtag> targetHashtag = hashtagRepository.findById(id);
        if (targetHashtag.isPresent()) {
            updatedHashtag.setId(id);
            return hashtagRepository.save(updatedHashtag);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'hashtag non esiste.");
        }
    }

    public void deleteHashtagById(Long id) { hashtagRepository.deleteById(id); }

    @Transactional
    public void deleteMultipleHashtags(List<Long> ids) {
        hashtagRepository.deleteHashtagsByIds(ids);
    }
}
