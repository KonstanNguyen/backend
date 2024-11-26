package com.systems.backend.service;

import com.systems.backend.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long roleId);
    Role createRole(Role role);
    Role updateRole(Long roleId, Role role);
    void deleteRole(Long roleId);
    void grantRole(Long roleId, Long userId);
}
