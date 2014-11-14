package com.visma.spring.controller;


import com.visma.spring.controller.AccountService;
import com.visma.spring.model.account.Account;
import com.visma.spring.model.account.Transaction;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InMemoryAcccountService implements AccountService {

    private List<Account> accounts = new ArrayList<Account>();


    @Override
    public Account createAccount() {
        Account account = new Account();
        accounts.add(account);
        account.setId((long)((accounts.size() - 1)));
        return account;
    }

    @Override
    public Optional<Account> getAccount(Long id) {
        return accounts.stream().filter((account) -> (account.getId().equals(id))).findAny();
    }

    @Override
    public Transaction addTransaction(Account account, Transaction transaction) {
        accounts.remove(account);
        if(transaction.getTimestamp() == null) {
            transaction.setTimestamp(LocalDateTime.now());
        }
        account.addTransaction(transaction);
        accounts.add(account);
        return transaction;
    }

    @Override
    public Optional<Transaction> getTransaction(Account account, long transactionId) {
        return null;
    }


    @Override
    public Transaction deleteTransaction(Account account, Transaction transaction) {
        return null;
    }
}
