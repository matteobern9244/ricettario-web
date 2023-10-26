package com.ricettario.Ricettario.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IngredientDTO {
    private Long id;
    private String name;
    private String description;
    private Boolean isArchivied;
    private List<ReceipeIngredientDTO> receipeIngredients;
}
