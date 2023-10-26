package com.ricettario.Ricettario.config.authentication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity // Automatically tells to spring to apply this configuration to application security
@EnableGlobalMethodSecurity(prePostEnabled = true) // Configuration used in order to restrict API access by Roles
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            // frontend static files
            "/", "/index.html", "/**/*.js",
            "/**/*.txt", "/**/*.js.map", "/**/*.css",
            "/**/*.scss","/**/*.html", "/**/*.ttf",
            "/**/*.json", "/**/*.woff", "/**/*.woff2",
            "/**/*.eot", "/**/*.svg", "/**/*.jpg",
            "/**/*.ico","/**/*.png"
    };

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;

    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;

    // AUTHENTICATION
    // configure method accepts a UserDetailsService as input (to be used for authentication purposes)
    // and manages password encoding, here specified.
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    // AUTHORIZATION
    // configure method accepts a HttpSecurity as input (to be used for authorization purposes).
    // Here the requests for which authorization is needed are specified with also the permitted ones (authenticate
    // and register).
    // AuthenticationEntryPoint is a custom class in order to handle Unauthorized requests.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/authenticate", "/api/v1/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    @Bean
    public static BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    // The AuthenticationManager is used simply for user authentication. Here it is allocated in the Spring App Context.
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    // JwtAuthenticationFilter "translates" JWT tokens (which contains also role information)
    // in something understandable to Spring.
    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

}
