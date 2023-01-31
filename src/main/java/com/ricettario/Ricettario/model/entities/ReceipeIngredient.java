package com.ricettario.Ricettario.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "receipe_ingredient")
public class ReceipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_receipe", referencedColumnName = "id")
    private Receipe receipe;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ingredient", referencedColumnName = "id")
    private Ingredient ingredient;

    private String qty;
    private Boolean isArchivied;
}
