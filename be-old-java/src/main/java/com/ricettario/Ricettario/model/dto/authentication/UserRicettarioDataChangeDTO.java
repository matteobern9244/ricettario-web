package com.ricettario.Ricettario.model.dto.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRicettarioDataChangeDTO {
    private String username;
    private String name;
    private String surname;
    private String email;
}
