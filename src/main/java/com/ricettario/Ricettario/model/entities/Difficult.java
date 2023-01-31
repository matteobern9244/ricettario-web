package com.ricettario.Ricettario.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "difficult")
public class Difficult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    private Boolean isArchivied;

    @OneToMany(mappedBy = "difficult", cascade = CascadeType.ALL)
    private Set<Receipe> receipes;
}
