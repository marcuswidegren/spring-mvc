package com.visma.spring.controller;

import com.visma.spring.model.account.Account;
import com.visma.spring.model.account.Transaction;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(@Qualifier(value = "persistentAccountService") AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/rest/account", method = RequestMethod.POST)
    public Account addAccount() {
        return accountService.createAccount();
    }

    @RequestMapping(value = "/rest/account/{id}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable Long id) {
        Optional<Account> account = accountService.getAccount(id);
        if(account.isPresent()) {
            return account.get();
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/rest/account/{id}/transaction", method = RequestMethod.POST)
    public Transaction addTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        return accountService.addTransaction(getAccount(id), transaction);
    }

    @RequestMapping(value = "/rest/account/{id}/transaction/{transactionId}", method = RequestMethod.GET)
    public Transaction getTransaction(@PathVariable Long id, @PathVariable long transactionId) {
        Account account = getAccount(id);
        Optional<Transaction> transaction = accountService.getTransaction(account, transactionId);
        if(transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/rest/account/{id}/transaction/{transactionId}", method = RequestMethod.DELETE)
    public Transaction deleteTransaction(@PathVariable Long id, @PathVariable long transactionId) {
        Account account = getAccount(id);
        Transaction transaction = getTransaction(id, transactionId);
        return accountService.deleteTransaction(account, transaction);
    }
}
