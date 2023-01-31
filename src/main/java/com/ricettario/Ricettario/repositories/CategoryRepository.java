package com.ricettario.Ricettario.repositories;

import com.ricettario.Ricettario.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Modifying
    @Query(value = "delete FROM category p WHERE p.id in ?1", nativeQuery = true)
    void deleteCategoriesByIds(List<Long> ids);
}
