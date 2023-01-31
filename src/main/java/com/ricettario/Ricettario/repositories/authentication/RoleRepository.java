package com.ricettario.Ricettario.repositories.authentication;

import com.ricettario.Ricettario.model.entities.authentication.Role;
import com.ricettario.Ricettario.model.entities.authentication.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByRoleType(RoleType roleType);
}
