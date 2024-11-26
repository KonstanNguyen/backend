package com.systems.backend.service.impl;

import com.systems.backend.model.Account;
import com.systems.backend.repository.AccountRepository;
import com.systems.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccountById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        return accountOptional.orElse(null);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(Account account) {
        Account checkAccount = getAccountById(account.getId());
        if (checkAccount != null) {
            throw new RuntimeException("Account already exists");
        }
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long accountId, Account account) {
        Account updateAccount = getAccountById(accountId);
        if (updateAccount == null) {
            throw new RuntimeException("Account is not found!");
        }

        updateAccount.setPassword(account.getPassword());

        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account1 = getAccountById(id);
        if (account1 == null) {
            throw new RuntimeException("Account is not found!");
        }
        accountRepository.delete(account1);
    }
}
