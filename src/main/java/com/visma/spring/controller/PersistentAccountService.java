package com.visma.spring.controller;

import com.visma.spring.model.account.Account;
import com.visma.spring.model.account.AccountDAO;
import com.visma.spring.model.account.Transaction;
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account createAccount() {
        return accountDAO.createAccount();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<Account> getAccount(Long id) {
        return accountDAO.getAccount(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Transaction addTransaction(Account account, Transaction transaction) {
        return accountDAO.addTransaction(account, transaction);
    }
}
