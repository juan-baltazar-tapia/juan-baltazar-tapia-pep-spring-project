package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

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

    public Optional<Account> userExists(int id) {
        return accountRepository.findById(id);
    }

    public  Optional<Account> userExistsByName(String name) {
        return accountRepository.findByUsername(name);
    }

    public Account getUserById(int id) {
        return accountRepository.getById(id);
    }

    public Account getUserByUsername(String name) {
        return accountRepository.getByUsername(name);
    }

}