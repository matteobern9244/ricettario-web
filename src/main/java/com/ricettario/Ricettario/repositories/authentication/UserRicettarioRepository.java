package com.ricettario.Ricettario.repositories.authentication;

import com.ricettario.Ricettario.model.entities.authentication.UserRicettario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface UserRicettarioRepository extends JpaRepository<UserRicettario, Long> {
    UserRicettario findByUsername(String username);

    @Modifying
    @Query(value = "delete FROM user_ricettario p WHERE p.id in ?1", nativeQuery = true)
    void deleteUsersRicettarioByIds(List<Long> ids);
}
