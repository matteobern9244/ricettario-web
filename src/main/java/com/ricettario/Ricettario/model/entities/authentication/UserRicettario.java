package com.ricettario.Ricettario.model.entities.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ricettario.Ricettario.model.entities.Receipe;
import com.ricettario.Ricettario.model.entities.ReceipeFavoritesUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user_ricettario")
public class UserRicettario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String Name;
    @Column(nullable = false)
    private String Surname;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    @Column(unique = true)
    private String email;
    private Date dateCreation;
    private Date dateModified;
    private Boolean blocked;
    private Boolean isAdmin;
    private Boolean isArchivied;

    @OneToMany(mappedBy = "userCreated", cascade = CascadeType.ALL)
    private Set<Receipe> receipes;

    @OneToMany(mappedBy = "userRicettario", cascade = CascadeType.ALL)
    private Set<ReceipeFavoritesUser> receipeFavoritesUsers;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"))
    private Set<Role> roles;
}
