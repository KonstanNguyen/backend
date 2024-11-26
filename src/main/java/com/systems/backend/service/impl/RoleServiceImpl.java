package com.systems.backend.service.impl;

import com.systems.backend.model.Account;
import com.systems.backend.model.DocUser;
import com.systems.backend.model.Role;
import com.systems.backend.repository.RoleRepository;
import com.systems.backend.service.AccountService;
import com.systems.backend.service.DocUserService;
import com.systems.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountService accountService;

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

    @Override
    public void grantRole(Long roleId, Long accountId) {
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            throw new RuntimeException("Account is not found!");
        }
        Role role = getRoleById(roleId);
        if (role == null) {
            throw new RuntimeException("Role is not found!");
        }

        Set<Account> accounts = new HashSet<>(role.getAccounts());
        accounts.add(account);
        role.setAccounts(accounts);

        roleRepository.save(role);
    }
}
