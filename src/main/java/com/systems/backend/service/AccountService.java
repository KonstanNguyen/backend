package com.systems.backend.service;

import com.systems.backend.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    Account getAccountById(Long id);
    List<Account> getAllAccounts();
    Account createAccount(Account account);
    Account updateAccount(Long accountId, Account account);
    void deleteAccount(Long id);
}
