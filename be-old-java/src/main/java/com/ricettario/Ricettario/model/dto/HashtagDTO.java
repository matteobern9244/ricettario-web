package com.ricettario.Ricettario.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HashtagDTO {
    private Long id;
    private String name;
    private List<ReceipeDTO> receipes;
}
