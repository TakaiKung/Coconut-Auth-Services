package com.example.authenticationservice.account.repository;

import com.example.authenticationservice.account.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    private String n;

    public AccountService (AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> retrieveAccount() {
        return accountRepository.findAllAccount();
    }

    public Account addAccount (Account account) {
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        account.setName(account.getName());
        account.setItems(account.getItems());
        return accountRepository.save(account);
    }

    public  Account getAccountAuth (String n) {return accountRepository.findAccountsByNameAuth(n);}
}
