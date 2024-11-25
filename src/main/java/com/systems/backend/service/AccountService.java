package com.systems.backend.service;

import com.systems.backend.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    public List<Account> getAllAccounts();
}
