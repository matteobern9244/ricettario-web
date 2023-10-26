package com.ricettario.Ricettario.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceipeIngredientDTO {
    private Long id;
    private ReceipeDTO receipe;
    private IngredientDTO ingredient;
    private String qty;
    private Boolean isArchivied;
}
