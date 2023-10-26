package com.ricettario.Ricettario.repositories;

import com.ricettario.Ricettario.model.entities.Difficult;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface DifficultRepository extends JpaRepository<Difficult, Long> {
    @Modifying
    @Query(value = "delete FROM difficult p WHERE p.id in ?1", nativeQuery = true)
    void deleteDifficultsByIds(List<Long> ids);
}
