package com.ricettario.Ricettario.model.dto.authentication;

import com.ricettario.Ricettario.model.dto.ReceipeDTO;
import com.ricettario.Ricettario.model.dto.ReceipeFavoritesUserDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserRicettarioDTO {
    private Long id;
    private String Name;
    private String Surname;
    private String username;
    private String password;
    private String email;
    private Date dateCreation;
    private Date dateModified;
    private Boolean blocked;
    private Boolean isAdmin;
    private Boolean isArchivied;
    private List<ReceipeDTO> receipes;
    private List<ReceipeFavoritesUserDTO> receipeFavoritesUsers;
    private List<RoleDTO> roles;
}
