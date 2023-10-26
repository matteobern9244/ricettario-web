package com.ricettario.Ricettario.repositories;

import com.ricettario.Ricettario.model.entities.Receipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceipeRepository extends JpaRepository<Receipe, Long> {
    @Modifying
    @Query(value = "delete FROM receipe p WHERE p.id in ?1", nativeQuery = true)
    void deleteReceipesByIds(List<Long> ids);
}
