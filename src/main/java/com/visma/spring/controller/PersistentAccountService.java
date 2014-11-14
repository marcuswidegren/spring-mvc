package com.visma.spring.controller;

import com.visma.cash.restmodel.Account;
import com.visma.cash.restmodel.Transaction;
import com.visma.spring.model.account.AccountDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import static com.visma.spring.model.ModelFactory.*;

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
        return fromDao(accountDAO.createAccount());
    }

    @Override
    @Transactional
    public Optional<Account> getAccount(Long id) {
        return fromDao(accountDAO.getAccount(id));
    }

    @Override
    @Transactional
    public Transaction addTransaction(Account account, Transaction transaction) {
        return fromDao(accountDAO.addTransaction(fromRest(account), fromRest(transaction)));
    }

    @Override
    public Optional<Transaction> getTransaction(Account account, long transactionId) {
        return account.getTransactions().stream().filter((Transaction transaction) -> transaction.getId() == transactionId).findAny();
    }

    @Override
    @Transactional
    public Transaction deleteTransaction(Account account, Transaction transaction) {
        accountDAO.deleteTransaction(fromRest(account), fromRest(transaction));
        return transaction;
    }
}
