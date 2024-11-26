package com.systems.backend.service;

import com.systems.backend.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {
    public Account getAccountById(Long id);
    public List<Account> getAllAccounts();
    public Account createAccount(Account account);
    public Account updateAccount(Account account);
    public void deleteAccount(Long id);
}
