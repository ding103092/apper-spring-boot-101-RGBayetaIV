package com.nocash.service.creditservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class CreditService {
    private final List<Account> accounts = new ArrayList<>();

    public Account createAccount(Double initialBalance) {
        String accountId = UUID.randomUUID().toString();
        Account account = new Account();
        account.setId(accountId);
        account.setBalance(initialBalance);
        accounts.add(account);
        return account;
    }

    public void addBalance(String accountId, Double amount) {
        for(Account account : accounts) {
            if(account.getId().equals(accountId)) {
                account.setBalance(account.getBalance()+amount);
                return;
            }
        }
    }
    public List<Account> getAccounts() {
        return accounts;
    }
}
