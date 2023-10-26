package com.ricettario.Ricettario.config;

import com.ricettario.Ricettario.model.entities.*;
import com.ricettario.Ricettario.model.entities.authentication.*;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Component
public class ExposeEntityIdRestMvcConfiguration  implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Category.class);
        config.exposeIdsFor(Cost.class);
        config.exposeIdsFor(Difficult.class);
        config.exposeIdsFor(Hashtag.class);
        config.exposeIdsFor(Ingredient.class);
        config.exposeIdsFor(Receipe.class);
        config.exposeIdsFor(ReceipeFavoritesUser.class);
        config.exposeIdsFor(ReceipeIngredient.class);
        config.exposeIdsFor(Role.class);
        config.exposeIdsFor(RoleType.class);
        config.exposeIdsFor(UserRicettario.class);
    }
}
