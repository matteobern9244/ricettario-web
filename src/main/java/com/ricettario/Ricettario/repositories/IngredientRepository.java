package com.ricettario.Ricettario.repositories;

import com.ricettario.Ricettario.model.entities.Ingredient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Modifying
    @Query(value = "delete FROM ingredient p WHERE p.id in ?1", nativeQuery = true)
    void deleteIngredientsByIds(List<Long> ids);
}
