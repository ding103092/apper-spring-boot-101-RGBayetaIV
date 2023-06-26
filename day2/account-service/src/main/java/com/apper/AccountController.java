package com.apper;

import com.apper.exception.AccountNotFoundException;
import com.apper.exception.NoRegisteredAccountsException;
import com.apper.exception.UsernameAlreadyRegisteredException;
import com.apper.request.CreateAccountRequest;
import com.apper.request.PutAccountRequest;
import com.apper.response.CreateAccountResponse;
import com.apper.response.DeleteAccountResponse;
import com.apper.response.GetAccountResponse;
import com.apper.response.PutAccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request)
            throws UsernameAlreadyRegisteredException {
        Account account = accountService.create(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword());

        CreateAccountResponse response = new CreateAccountResponse();
        response.setVerificationCode(account.getVerificationCode());

        return response;
    }

    @GetMapping("{accountId}")
    public GetAccountResponse getAccount(@PathVariable String accountId) throws AccountNotFoundException {
        Account account = accountService.get(accountId);

        return createGetAccountResponse(account);
    }

    @GetMapping
    public List<GetAccountResponse> getAllAccounts() throws NoRegisteredAccountsException {
        List<GetAccountResponse> responseList = new ArrayList<>();

        for (Account account : accountService.getAll()) {
            GetAccountResponse response = createGetAccountResponse(account);
            responseList.add(response);
        }

        return responseList;
    }

    private GetAccountResponse createGetAccountResponse(Account account) {
        GetAccountResponse response = new GetAccountResponse();
        response.setBalance(account.getBalance());
        response.setFirstName(account.getFirstName());
        response.setLastName(account.getLastName());
        response.setUsername(account.getUsername());
        response.setRegistrationDate(account.getCreationDate());
        response.setAccountId(account.getId());
        return response;
    }

    @DeleteMapping(path = "{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public DeleteAccountResponse deleteAccount(@PathVariable String accountId) throws AccountNotFoundException {
        accountService.delete(accountId);
        DeleteAccountResponse response = new DeleteAccountResponse();
        response.setDeleteMessage(accountId + " has been successfully deleted.");
        return response;
    }

    @PutMapping(path = "{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public PutAccountResponse putAccount(
            @RequestBody PutAccountRequest request,
            @PathVariable String accountId)
            throws AccountNotFoundException {
        accountService.update(
                accountId,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword());

        PutAccountResponse response = new PutAccountResponse();
        response.setLastUpdated(accountService.get(accountId).getLastUpdated());

        return response;
    }
}
