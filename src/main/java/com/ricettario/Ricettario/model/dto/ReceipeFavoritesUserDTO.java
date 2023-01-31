package com.ricettario.Ricettario.model.dto;

import com.ricettario.Ricettario.model.dto.authentication.UserRicettarioDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceipeFavoritesUserDTO {
    private Long id;
    private ReceipeDTO receipe;
    private UserRicettarioDTO user;
}
