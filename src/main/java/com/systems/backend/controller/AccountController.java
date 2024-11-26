package com.systems.backend.controller;

import com.systems.backend.model.Account;
import com.systems.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountService.createAccount(account));
    }

    @GetMapping("{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable(name = "accountId") Long accountId) {
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

    @RequestMapping(value = "{accountId}/update", method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<Account> updateAccount(@PathVariable(name = "accountId") Long accountId, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.updateAccount(accountId, account));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{accountId}/delete")
    public void deleteAccount(@PathVariable(name = "accountId") Long accountId) {
        accountService.deleteAccount(accountId);
    }
}
