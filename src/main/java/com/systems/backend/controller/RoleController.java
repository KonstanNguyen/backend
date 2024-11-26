package com.systems.backend.controller;

import com.systems.backend.model.Role;
import com.systems.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    private final RoleService roleService;

    public RoleController(@Qualifier("roleService") RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> accounts = roleService.getAllRoles();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role account) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(roleService.createRole(account));
    }

    @GetMapping("{accountId}")
    public ResponseEntity<Role> getRole(@PathVariable Long accountId) {
        return ResponseEntity.ok(roleService.getRoleById(accountId));
    }

    @RequestMapping(value = "{accountId}/update", method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<Role> updateRole(@PathVariable Long accountId, @RequestBody Role account) {
        return ResponseEntity.ok(roleService.updateRole(account));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{accountId}/delete")
    public void deleteRole(@PathVariable Long accountId) {
        roleService.deleteRole(accountId);
    }
}
