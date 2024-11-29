package com.systems.backend.controller;

import com.systems.backend.model.Account;
import com.systems.backend.model.DocUser;
import com.systems.backend.repository.RoleRepository;
import com.systems.backend.requests.CreateDocUserRequest;
import com.systems.backend.requests.LoginRequest;
import com.systems.backend.requests.RegisterRequest;
import com.systems.backend.responses.ApiResponse;
import com.systems.backend.responses.LoginResponse;
import com.systems.backend.security.JwtGenerator;
import com.systems.backend.service.AccountService;
import com.systems.backend.service.DocUserService;
import com.systems.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerator jwtGenerator;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountService.createAccount(registerRequest));
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

    @PostMapping("login")
    public ResponseEntity<?> loginAccount(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.accepted()
                .header(HttpHeaders.SET_COOKIE, "Bearer " + token)
                .body(ApiResponse.builder()
                        .data(token)
                        .message("Login successful")
                        .code(HttpStatus.OK.value())
                        .build()
                );
    }

    @PostMapping("register")
    public ResponseEntity<?> registerAccount(@RequestBody RegisterRequest registerRequest) {
        if (accountService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.builder()
                            .message("Username already exists")
                            .code(HttpStatus.BAD_REQUEST.value())
                            .build()
                    );
        }
        accountService.registerAccount(registerRequest);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Account successfully registered!")
                .code(HttpStatus.OK.value())
                .build()
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {

        return ResponseEntity.ok(ApiResponse.builder()
                .message("Logout successful")
                .code(HttpStatus.OK.value())
        );
    }
}
