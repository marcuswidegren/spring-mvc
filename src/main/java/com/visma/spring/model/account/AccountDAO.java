package com.visma.spring.model.account;

import java.util.Optional;

public interface AccountDAO {

    public Account createAccount();

    public Optional<Account> getAccount(long id);

    public Transaction addTransaction(Account account, Transaction transaction);

    public void persistAccount(Account account);

}
