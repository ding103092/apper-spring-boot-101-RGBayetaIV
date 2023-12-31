package com.apper;

import com.apper.exception.AccountNotFoundException;
import com.apper.exception.NoRegisteredAccountsException;
import com.apper.exception.UsernameAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final List<Account> accounts = new ArrayList<>();

    private final IdGeneratorService idGeneratorService;

    @Autowired
    public AccountService(IdGeneratorService idGeneratorService) {
        this.idGeneratorService = idGeneratorService;
    }

    public Account create(String firstName,
                          String lastName,
                          String username,
                          String clearPassword)
            throws UsernameAlreadyRegisteredException {

        if (isUsernameRegistered(username)) {
            throw new UsernameAlreadyRegisteredException("Username already registered.");
        }

        Account account = new Account();

        String id = idGeneratorService.getNextId();
        System.out.println("Generated id: " + id);

        account.setId(id);
        account.setBalance(1_000.0);

        LocalDateTime now = LocalDateTime.now();
        account.setCreationDate(now);
        account.setLastUpdated(now);

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(clearPassword);
        account.setVerificationCode(idGeneratorService.generateRandomCharacters(6));

        accounts.add(account);

        return account;
    }

    public Account get(String accountId) throws AccountNotFoundException {
        Account account = findAccountById(accountId);
        if (account == null) {
            throw new AccountNotFoundException("Account not found.");
        }
        return account;
    }

    public List<Account> getAll() throws NoRegisteredAccountsException {
        if(accounts.isEmpty()) throw new NoRegisteredAccountsException("No registered accounts.");
        return accounts;
    }

    public void update(String id,
                       String firstName,
                       String lastName,
                       String username,
                       String clearPassword) {
        boolean isUpdated = false;
        for (int i = 0; i < accounts.size(); i++) {
            Account updatedAccount = accounts.get(i);
            if(updatedAccount.getId().equals(id)) {
                if(firstName != null) {
                    updatedAccount.setFirstName(firstName);
                    isUpdated = true;
                }
                if(lastName != null) {
                    updatedAccount.setLastName(lastName);
                    isUpdated = true;
                }
                if(username != null) {
                    updatedAccount.setUsername(username);
                    isUpdated = true;
                }
                if(clearPassword != null) {
                    updatedAccount.setClearPassword(clearPassword);
                    isUpdated = true;
                }

                if(isUpdated) {
                    updatedAccount.setLastUpdated(LocalDateTime.now()); // Update time of last update
                    accounts.set(i, updatedAccount); // Update the account in the list
                }
                break; // Done with the update
            }
        }
    }

    public void delete(String id) throws AccountNotFoundException {
        Account accountToDelete = get(id);
        if(accountToDelete != null) accounts.remove(accountToDelete);
    }

    /* HELPER FUNCTIONS */
    private Account findAccountById(String accountId) {
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public boolean isUsernameRegistered(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
