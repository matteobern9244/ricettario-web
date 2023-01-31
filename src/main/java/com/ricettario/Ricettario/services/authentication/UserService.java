package com.ricettario.Ricettario.services.authentication;

import com.ricettario.Ricettario.model.dto.authentication.*;
import com.ricettario.Ricettario.model.entities.authentication.Role;
import com.ricettario.Ricettario.model.entities.authentication.UserRicettario;
import com.ricettario.Ricettario.repositories.authentication.RoleRepository;
import com.ricettario.Ricettario.repositories.authentication.UserRicettarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
public class UserService implements UserDetailsService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRicettarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRicettario user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserRicettario user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleType().name()));
        });
        return authorities;
    }

    public List<UserRicettario> findAll() {
        List<UserRicettario> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public UserRicettario findOne(String username) {
        return userRepository.findByUsername(username);
    }

    public UserRicettarioDTO save(UserRicettarioFormDTO user) {
        UserRicettario nUser = modelMapper.map(user, UserRicettario.class);
        nUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return modelMapper.map(userRepository.save(nUser), UserRicettarioDTO.class);
    }

    public Role convertToRole(RoleDTO role) {
        return modelMapper.map(role, Role.class);
    }

    public UserRicettarioDTO convertToUserRicettarioDTO(UserRicettario userRicettario) {
        return modelMapper.map(userRicettario, UserRicettarioDTO.class);
    }

    public UserRicettario convertToUserRicettarioEntity(UserRicettarioDTO userRicettario) {
        return modelMapper.map(userRicettario, UserRicettario.class);
    }

    public UserRicettario updateUserRicettario(Long id, UserRicettarioDTO updatedUserRicettario) {
        Optional<UserRicettario> targetUserRicettario = userRepository.findById(id);
        if (targetUserRicettario.isPresent()) {
            UserRicettario user = modelMapper.map(updatedUserRicettario, UserRicettario.class);
            user.setId(targetUserRicettario.get().getId());
            user.setPassword(targetUserRicettario.get().getPassword());
            return userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'utente non esiste.");
        }
    }

    public void updateUserRicettarioPassword(Long id, PasswordChangeDTO passwordChangeUser) {
        String oldPassword = passwordChangeUser.getOldPassword(),
                newPassw = passwordChangeUser.getNewPassword();
        Optional<UserRicettario> targetUserRicettario = userRepository.findById(id);
        if (targetUserRicettario.isPresent()) {
            UserRicettario user = targetUserRicettario.get();
            if(bCryptPasswordEncoder.matches(oldPassword,user.getPassword())) {
                user.setId(targetUserRicettario.get().getId());
                user.setPassword(bCryptPasswordEncoder.encode(newPassw));
                userRepository.save(user);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'utente non esiste.");
        }
        return;
    }

    public void updateUserRicettarioData(Long id, UserRicettarioDataChangeDTO userDataChangeDTO) {
        Optional<UserRicettario> targetUserRicettario = userRepository.findById(id);
        if(targetUserRicettario.isPresent()) {
            UserRicettario user = modelMapper.map(userDataChangeDTO, UserRicettario.class);
            user.setId(targetUserRicettario.get().getId());
            user.setPassword(targetUserRicettario.get().getPassword());
            user.setRoles(targetUserRicettario.get().getRoles());
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'utente non esiste.");
        }
    }

    public List<UserRicettario> getUsersRicettario() {
        // Get di tutti gli utenti salvati nel DB tranne l'utente con ID 0: quello è il root user,
        // che non deve essere mostrato nella lista degli utenti nella GUI
        return new ArrayList<>(userRepository.findAll())
                .stream().filter(user -> user.getId() != 0)
                .collect(Collectors.toList());
    }

    public UserRicettario getUserRicettarioById(Long id) {
        Optional<UserRicettario> userRicettario = userRepository.findById(id);
        if (userRicettario.isPresent()) {
            return userRicettario.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'utente non esiste.");
        }
    }

    public void deleteUserRicettarioById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void deleteMultipleUsersRicettario(List<Long> ids) {
        userRepository.deleteUsersRicettarioByIds(ids);
    }

    // Needed when generating token, after a successfully authentication
    // (see public ResponseEntity<?> generateToken(@RequestBody LoginAnciUserDTO loginUser) in AuthenticationController
    public UserRicettario retrieveByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}