package com.ricettario.Ricettario.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "cost")
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    private Boolean isArchivied;

    @OneToMany(mappedBy = "cost", cascade = CascadeType.ALL)
    private Set<Receipe> receipes;
}
