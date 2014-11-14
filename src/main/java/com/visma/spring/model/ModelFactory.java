package com.visma.spring.model;

import com.visma.spring.model.account.Account;
import com.visma.spring.model.account.Transaction;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Translates from/to rest entities to hibernate entities
 */
public class ModelFactory {

    public static Account fromRest(com.visma.cash.restmodel.Account accountFromRest) {
        return new Account(accountFromRest.getId(), accountFromRest.getAmount(), fromRest(accountFromRest.getTransactions()));
    }

    public static Transaction fromRest(com.visma.cash.restmodel.Transaction transactionFromRest) {
        return new Transaction(transactionFromRest.getId(), transactionFromRest.getAmount(), transactionFromRest.getTimestamp(), transactionFromRest.getCategory());
    }

    private static Set<Transaction> fromRest(Collection<com.visma.cash.restmodel.Transaction> transactions) {
        Set<Transaction> resultingTransactions = new HashSet<>();
        for(com.visma.cash.restmodel.Transaction transaction : transactions) {
            resultingTransactions.add(fromRest(transaction));
        }
        return resultingTransactions;
    }

    public static com.visma.cash.restmodel.Account fromDao(Account accountFromDao) {
        return new com.visma.cash.restmodel.Account(accountFromDao.getId(), fromDao(accountFromDao.getTransactions()), accountFromDao.getAmount());
    }

    public static Optional<com.visma.cash.restmodel.Account> fromDao(Optional<Account> accountFromDao) {
        if(accountFromDao.isPresent()) {
            Account account = accountFromDao.get();
            return Optional.of(new com.visma.cash.restmodel.Account(account.getId(), fromDao(account.getTransactions()), account.getAmount()));
        } else {
            return Optional.empty();
        }
    }

    public static com.visma.cash.restmodel.Transaction fromDao(Transaction transactionFromDao) {
        return new com.visma.cash.restmodel.Transaction(transactionFromDao.getId(), transactionFromDao.getAmount(), transactionFromDao.getTimestamp(), transactionFromDao.getCategory());
    }

    private static Set<com.visma.cash.restmodel.Transaction> fromDao(Set<Transaction> transactions) {
        Set<com.visma.cash.restmodel.Transaction> resultingTransactions = new HashSet<>();
        for(Transaction transaction : transactions) {
            resultingTransactions.add(fromDao(transaction));
        }
        return resultingTransactions;
    }


}
