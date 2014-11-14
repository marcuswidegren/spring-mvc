package com.visma.spring.controller;

import com.visma.spring.model.account.Account;
import com.visma.spring.model.account.AccountDAO;
import com.visma.spring.model.account.Transaction;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@EnableTransactionManagement
public class PersistentAccountService implements AccountService {

    private final AccountDAO accountDAO;

    @Autowired
    public PersistentAccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    @Transactional
    public Account createAccount() {
        return accountDAO.createAccount();
    }

    @Override
    @Transactional
    public Optional<Account> getAccount(Long id) {
        return accountDAO.getAccount(id);
    }

    @Override
    @Transactional
    public Transaction addTransaction(Account account, Transaction transaction) {
        if(transaction.getTimestamp() == null) {
            transaction.setTimestamp(LocalDateTime.now());
        }
        return accountDAO.addTransaction(account, transaction);
    }

    @Override
    public Optional<Transaction> getTransaction(Account account, long transactionId) {
        return account.getTransactions().stream().filter((Transaction transaction) -> transaction.getId() == transactionId).findAny();
    }

    @Override
    @Transactional
    public Transaction deleteTransaction(Account account, Transaction transaction) {
        account.getTransactions().remove(transaction);
        accountDAO.persistAccount(account);
        return transaction;
    }
}
