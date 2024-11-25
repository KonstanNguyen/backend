package com.systems.backend.service.impl;

import com.systems.backend.repository.RoleRepository;
import com.systems.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
}
