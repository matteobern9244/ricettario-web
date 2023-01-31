package com.ricettario.Ricettario.model.dto.authentication;

public class UserRicettarioAuthTokenDTO {

    private String token;

    public UserRicettarioAuthTokenDTO() { }

    public UserRicettarioAuthTokenDTO(String token) {
        this.token = token;
    }

    public String getToken() { return token; }

    public void SetToken(String token) {
        this.token = token;
    }
}
