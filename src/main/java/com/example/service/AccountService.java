package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    /**
     * Given a brand new transient Account (meaning, no such Account exists yet in the database),
     * persist the Account to the database (create a new database record for the Account entity.)
     */
    public Account persistAccount(Account account){
        return accountRepository.save(account);
    }

    // public Optional<Account> userExists(boolean b) {
    //     return accountRepository.findById(b);
    // }
}