package com.ricettario.Ricettario.repositories;


import com.ricettario.Ricettario.model.entities.ReceipeFavoritesUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceipeFavoritesUserRepository extends JpaRepository<ReceipeFavoritesUser, Long> {
    @Modifying
    @Query(value = "delete FROM receipe_favorites_user p WHERE p.id in ?1", nativeQuery = true)
    void deleteReceipesFavoritesUsersByIds(List<Long> ids);
}
