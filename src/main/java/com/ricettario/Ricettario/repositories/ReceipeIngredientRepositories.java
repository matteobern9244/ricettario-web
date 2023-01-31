package com.ricettario.Ricettario.repositories;

import com.ricettario.Ricettario.model.entities.ReceipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceipeIngredientRepositories extends JpaRepository<ReceipeIngredient, Long> {
    @Modifying
    @Query(value = "delete FROM receipe_ingredient p WHERE p.id in ?1", nativeQuery = true)
    void deleteReceipesIngredientsByIds(List<Long> ids);
}
