package com.visma.spring.controller;

import com.visma.spring.model.account.Account;
import com.visma.spring.model.account.Transaction;

import java.util.Optional;

public interface AccountService {

    public Account createAccount();

    public Optional<Account> getAccount(Long id);

    public Transaction addTransaction(Account account, Transaction transaction);

}
