package com.ricettario.Ricettario.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    private Boolean isArchivied;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private Set<ReceipeIngredient> receipeIngredients;
}
