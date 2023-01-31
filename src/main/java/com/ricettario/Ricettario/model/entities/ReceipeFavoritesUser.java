package com.ricettario.Ricettario.model.entities;

import com.ricettario.Ricettario.model.entities.authentication.UserRicettario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="receipe_favorites_user")
public class ReceipeFavoritesUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_receipe", referencedColumnName = "id")
    private Receipe receipe;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_userRicettario", referencedColumnName = "id")
    private UserRicettario userRicettario;
}