package com.ricettario.Ricettario.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CostDTO {
    private Long id;
    private String name;
    private Boolean isArchivied;
    private List<ReceipeDTO> receipes;
}
