package com.systems.backend.service;

import com.systems.backend.model.Account;
import com.systems.backend.requests.RegisterRequest;
import com.systems.backend.responses.RegisterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    Boolean existsByUsername(String username);
    Account getAccountById(Long id);
    Account getAccountByUsername(String username);
    List<Account> getAllAccounts();
    Account createAccount(RegisterRequest registerRequest);
    Account updateAccount(Long accountId, Account account);
    void deleteAccount(Long id);
    RegisterResponse registerAccount(RegisterRequest registerRequest);
}
