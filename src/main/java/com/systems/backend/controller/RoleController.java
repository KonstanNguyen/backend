package com.systems.backend.controller;

import com.systems.backend.model.Role;
import com.systems.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> accounts = roleService.getAllRoles();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(roleService.createRole(role));
    }

    @GetMapping("{roleId}")
    public ResponseEntity<Role> getRole(@PathVariable(name = "roleId") Long roleId) {
        return ResponseEntity.ok(roleService.getRoleById(roleId));
    }

    @RequestMapping(value = "{roleId}/update", method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<Role> updateRole(@PathVariable(name = "roleId") Long roleId, @RequestBody Role role) {
        return ResponseEntity.ok(roleService.updateRole(roleId, role));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{roleId}/delete")
    public void deleteRole(@PathVariable(name = "roleId") Long accountId) {
        roleService.deleteRole(accountId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("{roleId}/grant/{docUserId}")
    public void grantRole(@PathVariable(name = "roleId") Long roleId, @PathVariable(name = "docUserId") Long docUserId) {
        roleService.grantRole(roleId, docUserId);
    }
}
