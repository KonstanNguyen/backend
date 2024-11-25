package com.systems.backend.service.impl;

import com.systems.backend.repository.AccountRepository;
import com.systems.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
}
