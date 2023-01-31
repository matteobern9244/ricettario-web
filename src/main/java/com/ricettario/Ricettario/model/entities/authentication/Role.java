package com.ricettario.Ricettario.model.entities.authentication;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @ManyToMany(mappedBy = "roles")
    private Set<UserRicettario> usersRicettario;
}
