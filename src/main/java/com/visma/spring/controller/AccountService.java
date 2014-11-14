package com.visma.spring.controller;

import com.visma.cash.restmodel.Account;
import com.visma.cash.restmodel.Transaction;

import java.util.Optional;


public interface AccountService {

    public Account createAccount();

    public Optional<Account> getAccount(Long id);

    public Transaction addTransaction(Account account, Transaction transaction);

    public Optional<Transaction> getTransaction(Account account, long transactionId);

    public Transaction deleteTransaction(Account account, Transaction transaction);

}
