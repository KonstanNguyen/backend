package com.systems.backend.service.impl;

import com.systems.backend.repository.DocUserRepository;
import com.systems.backend.service.DocUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocUserServiceImpl implements DocUserService {
    @Autowired
    private DocUserRepository docUserRepository;
}
