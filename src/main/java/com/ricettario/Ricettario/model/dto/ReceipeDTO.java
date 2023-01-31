package com.ricettario.Ricettario.model.dto;

import com.ricettario.Ricettario.model.dto.authentication.UserRicettarioDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReceipeDTO {
    private Long id;
    private String title;
    private String description;
    private String preparation;
    private Date dateCreation;
    private Date dateUpdated;
    private String timePreparation;
    private String timeCooking;
    private String timeRest;
    private String link;
    private String advise;
    private String calories;
    private String notes;
    private Boolean isArchivied;

    private CategoryDTO category;
    private DifficultDTO difficult;
    private CostDTO cost;
    private UserRicettarioDTO userCreated;
    private List<HashtagDTO> hashtags;
    private List<ReceipeIngredientDTO> receipeIngredients;
    private List<ReceipeFavoritesUserDTO> receipeFavoritesUsers;
}
