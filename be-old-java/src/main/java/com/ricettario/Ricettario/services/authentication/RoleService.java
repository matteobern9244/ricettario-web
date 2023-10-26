package com.ricettario.Ricettario.services.authentication;

import com.ricettario.Ricettario.model.dto.authentication.RoleDTO;
import com.ricettario.Ricettario.model.entities.authentication.Role;
import com.ricettario.Ricettario.model.entities.authentication.RoleType;
import com.ricettario.Ricettario.repositories.authentication.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "roleService")
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Role findbyRoleType(RoleType roleType) { return roleRepository.findRoleByRoleType(roleType); }

    public List<Role> getAllRoles() { return roleRepository.findAll(); }

    public RoleDTO convertToRoleDTO(Role role) { return modelMapper.map(role,RoleDTO.class); }
}
