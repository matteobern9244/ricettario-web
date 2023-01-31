package com.ricettario.Ricettario.model.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "hashtag")
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "hashtags")
    private Set<Receipe> receipes;
}
