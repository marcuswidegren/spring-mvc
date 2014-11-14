package com.visma.spring.model.account;

import org.hibernate.SessionFactory;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
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
        account.setAmount(Money.zero(CurrencyUnit.EUR));
        sessionFactory.getCurrentSession().persist(account);
        return account;
    }

    @Override
    public Optional<Account> getAccount(long id) {
        return Optional.ofNullable((Account)sessionFactory.getCurrentSession().get(Account.class, id));
    }

    public Transaction addTransaction(Account account, Transaction transaction) {
        account.addTransaction(transaction);
        sessionFactory.getCurrentSession().update(account);
        return transaction;
    }

    public void persistAccount(Account account) {
        sessionFactory.getCurrentSession().update(account);
    }

    @Override
    public void deleteTransaction(Account account, Transaction transaction) {
        account.getTransactions().remove(transaction);
        persistAccount(account);
    }

}
