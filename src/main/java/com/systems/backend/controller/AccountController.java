package com.systems.backend.controller;

import com.systems.backend.model.Account;
import com.systems.backend.requests.LoginRequest;
import com.systems.backend.requests.RegisterRequest;
import com.systems.backend.responses.ApiResponse;
import com.systems.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("{accountId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Account getAccount(@PathVariable(name = "accountId") Long accountId) {
        return accountService.getAccountById(accountId);
    }

    @RequestMapping(value = "{accountId}/update", method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Account updateAccount(
            @PathVariable(name = "accountId") Long accountId,
            @RequestBody Account account
    ) {
        return accountService.updateAccount(accountId, account);
    }

    @DeleteMapping("{accountId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable(name = "accountId") Long accountId) {
        accountService.deleteAccount(accountId);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResponse<Object> loginAccount(@RequestBody LoginRequest loginRequest) {
        return ApiResponse.builder()
                .data(accountService.loginAccount(loginRequest))
                .message("Login successful")
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResponse<Object> registerAccount(@RequestBody RegisterRequest registerRequest) {
        return ApiResponse.builder()
                .data(accountService.registerAccount(registerRequest))
                .message("Account successfully registered!")
                .code(HttpStatus.OK.value())
                .build();
    }

//    @PostMapping("/logout")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public ApiResponse<?> logout() {
//
//        return ApiResponse.builder()
//                .message("Logout successful")
//                .code(HttpStatus.OK.value())
//                .build();
//    }
}
