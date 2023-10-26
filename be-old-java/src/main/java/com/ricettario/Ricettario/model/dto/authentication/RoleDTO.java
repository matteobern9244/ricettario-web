package com.ricettario.Ricettario.model.dto.authentication;

import com.ricettario.Ricettario.model.entities.authentication.RoleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {
    private Long id;
    private RoleType roleType;
}