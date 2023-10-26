package com.ricettario.Ricettario.model.entities;

import com.ricettario.Ricettario.model.entities.authentication.UserRicettario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "receipe")
public class Receipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
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

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_category", referencedColumnName = "id")
    private Category category;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_difficult", referencedColumnName = "id")
    private Difficult difficult;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_cost", referencedColumnName = "id")
    private Cost cost;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_userRicettario", referencedColumnName = "id")
    private UserRicettario userCreated;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "receipe_hashtag",
            inverseJoinColumns = @JoinColumn(name = "id_hashtag", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "id_receipe", referencedColumnName = "id"))
    private Set<Hashtag> hashtags;

    @OneToMany(mappedBy = "receipe", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<ReceipeIngredient> receipeIngredients = new HashSet<>();

    @OneToMany(mappedBy = "receipe", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<ReceipeFavoritesUser> receipeFavoritesUsers = new HashSet<>();

    //private ReceipeImage receipeImage;
}
