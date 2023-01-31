package com.ricettario.Ricettario.repositories;

import com.ricettario.Ricettario.model.entities.Cost;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface CostRepository extends JpaRepository<Cost, Long> {
    @Modifying
    @Query(value = "delete FROM cost p WHERE p.id in ?1", nativeQuery = true)
    void deleteCostsByIds(List<Long> ids);
}
