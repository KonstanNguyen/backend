package com.systems.backend.service.impl;

import com.systems.backend.model.Role;
import com.systems.backend.repository.RoleRepository;
import com.systems.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long roleId) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        return roleOptional.orElse(null);
    }

    @Override
    public Role createRole(Role role) {
        Role checkRole = getRoleById(role.getId());
        if (checkRole != null) {
            throw new RuntimeException("Role already exists");
        }
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long roleId, Role role) {
        Role updateRole = getRoleById(role.getId());
        if (updateRole == null) {
            throw new RuntimeException("Role is not exists");
        }

        updateRole.setName(role.getName());
        updateRole.setDescription(role.getDescription());

        return null;
    }

    @Override
    public void deleteRole(Long roleId) {
        Role role = getRoleById(roleId);
        if (role == null) {
            throw new RuntimeException("Role is not found!");
        }
        roleRepository.delete(role);
    }
}
