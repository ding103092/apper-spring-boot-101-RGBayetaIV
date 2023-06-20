package com.nocash.service.creditservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/accounts")
public class CreditController {
    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    // Create new account
    @PostMapping
    public Account createNewAccount(@RequestBody Account account) {
        Double initialBalance = account.getBalance();
        return creditService.createAccount(initialBalance);
    }

    // Retrieve all accounts
    @GetMapping
    public List<Account> getAccounts() {
        // For testing purposes only
//        this.creditService.createAccount(200.0);
//        this.creditService.createAccount(201.0);
//        this.creditService.createAccount(202.0);
//        this.creditService.createAccount(203.0);
//        this.creditService.createAccount(204.0);
        return creditService.getAccounts();
    }
}
