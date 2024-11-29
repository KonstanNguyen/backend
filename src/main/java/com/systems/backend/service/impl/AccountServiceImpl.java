package com.systems.backend.service.impl;

import com.systems.backend.constants.JwtConstants;
import com.systems.backend.model.Account;
import com.systems.backend.model.DocUser;
import com.systems.backend.model.Role;
import com.systems.backend.repository.AccountRepository;
import com.systems.backend.repository.DocUserRepository;
import com.systems.backend.repository.RoleRepository;
import com.systems.backend.requests.RegisterRequest;
import com.systems.backend.responses.RegisterResponse;
import com.systems.backend.security.JwtGenerator;
import com.systems.backend.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DocUserRepository docUserRepository;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Boolean existsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public Account getAccountById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        return accountOptional.orElseThrow(() ->
                new ResourceNotFoundException("Account not found")
        );
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("Account with username " + username + " not found")
        );
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(RegisterRequest registerRequest) {


        Account account = Account.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .user(
                        DocUser.builder()
                                .name(registerRequest.getUsername())
                                .email(registerRequest.getEmail())
                                .phone(registerRequest.getPhone())
                                .dateOfBirth(LocalDate.parse(registerRequest.getBirthday()))
                                .gender(registerRequest.getGender())
                                .build()
                )
                .build();

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        account.setRoles(List.of(userRole));

        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long accountId, Account account) {
        Account updateAccount = getAccountById(accountId);

        updateAccount.setUsername(account.getUsername());
        updateAccount.setPassword(account.getPassword());
        updateAccount.setUser(account.getUser());
        updateAccount.setRoles(account.getRoles());

        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        Account deleteAccount = getAccountById(id);
        accountRepository.delete(deleteAccount);
    }

    @Override
    public RegisterResponse registerAccount(RegisterRequest registerRequest) {
        DocUser docUser = DocUser.builder()
                .name(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhone())
                .dateOfBirth(LocalDate.parse(registerRequest.getBirthday()))
                .gender(registerRequest.getGender())
                .build();

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        Account account = Account.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .user(docUser)
                .roles(List.of(userRole))
                .build();

        accountRepository.save(account);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getUsername(), registerRequest.getPassword())
        );
        String token = jwtGenerator.generateToken(authentication);

        return RegisterResponse.builder()
                .username(registerRequest.getUsername())
                .token(token)
                .expiresIn(JwtConstants.EXPIRATION_TIME)
                .build();
    }
}
