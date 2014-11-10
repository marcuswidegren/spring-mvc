package com.visma.spring.model.account;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public AccountDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Account createAccount() {
        Account account = new Account();
        sessionFactory.getCurrentSession().persist(account);
        return account;
    }

    @Override
    public Optional<Account> getAccount(long id) {
        return Optional.ofNullable((Account)sessionFactory.getCurrentSession().get(Account.class, id));
    }

    @Override
    public Transaction addTransaction(Account account, Transaction transaction) {
        account.addTransaction(transaction);
        sessionFactory.getCurrentSession().update(account);
        return transaction;
    }

}
