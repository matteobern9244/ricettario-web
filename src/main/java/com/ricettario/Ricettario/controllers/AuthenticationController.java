package com.ricettario.Ricettario.controllers;

import com.ricettario.Ricettario.config.authentication.TokenProvider;
import com.ricettario.Ricettario.model.dto.authentication.*;
import com.ricettario.Ricettario.model.entities.authentication.UserRicettario;
import com.ricettario.Ricettario.services.Mappers;
import com.ricettario.Ricettario.services.authentication.UserService;
import com.ricettario.Ricettario.services.authentication.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Mappers mapper;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    UserService userRicettarioService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUserRicettarioDTO loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        // Retrieve successfully authenticated user in order to encode more useful user information
        UserRicettario userRicettario = userRicettarioService.retrieveByUsername(loginUser.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication, userRicettario);
        return ResponseEntity.ok(new UserRicettarioAuthTokenDTO(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserRicettarioDTO saveUser(@RequestBody UserRicettarioFormDTO user) {
        return userRicettarioService.save(user);
    }

    @PutMapping("/{id}")
    public UserRicettarioDTO updateUserRicettario(@PathVariable("id") Long id, @RequestBody UserRicettarioDTO updatedUserRicettario) {
        UserRicettario ur = userRicettarioService.updateUserRicettario(id, updatedUserRicettario);
        return userRicettarioService.convertToUserRicettarioDTO(ur);
    }

    @PutMapping("/change-password/{id}")
    public void updateUserRicettarioPassword(@PathVariable("id") Long id, @RequestBody PasswordChangeDTO passwordChangeUser) {
        userRicettarioService.updateUserRicettarioPassword(id, passwordChangeUser);
    }

    @PutMapping("/change-user-data/{id}")
    public void updateUserRicettarioData(@PathVariable("id") Long id, @RequestBody UserRicettarioDataChangeDTO userDataChangeDTO) {
        userRicettarioService.updateUserRicettarioData(id, userDataChangeDTO);
    }

    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    @DeleteMapping("/{id}")
    public void deleteUserRicettarioById(@PathVariable("id") Long id) {
        userRicettarioService.deleteUserRicettarioById(id);
    }

    @PreAuthorize("hasRole('ADMIN_RICETTARIO')")
    @DeleteMapping("/multiple-delete")
    public void deleteMultipleUsersRicettario(@RequestParam(value = "ids") Long[] ids) {
        userRicettarioService.deleteMultipleUsersRicettario(Arrays.asList(ids));
    }

    @GetMapping
    @ResponseBody
    public List<UserRicettarioDTO> getUsersRicettario() {
        List<UserRicettario> usersRicettario = userRicettarioService.getUsersRicettario();
        return usersRicettario.stream()
                .map(userRicettarioService::convertToUserRicettarioDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserRicettarioDTO getUserRicettarioDTO(@PathVariable("id") Long id) {
        return userRicettarioService.convertToUserRicettarioDTO(userRicettarioService.getUserRicettarioById(id));
    }

    @GetMapping("/roles")
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles().stream()
                .map(roleService::convertToRoleDTO)
                .collect(Collectors.toList());
    }
}