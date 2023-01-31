package com.ricettario.Ricettario.repositories;

import com.ricettario.Ricettario.model.entities.Hashtag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    @Modifying
    @Query(value = "delete FROM hashtag p WHERE p.id in ?1", nativeQuery = true)
    void deleteHashtagsByIds(List<Long> ids);
}
