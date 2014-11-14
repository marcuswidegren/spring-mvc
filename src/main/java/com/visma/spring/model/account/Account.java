package com.visma.spring.model.account;

import org.joda.money.Money;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.*;

@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @OneToMany(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    @Column(nullable = false)
    private Money totalAmount;

    public Money getAmount() {
        return totalAmount;
    }

    public void setAmount(Money amount) {
        this.totalAmount = amount;
    }

    public Account(Long id) {
        this.id = id;
    }

    public Account(Long id, Money totalAmount, Set<Transaction> transactions) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.transactions = transactions;
    }

    protected Account(){} // for hibernate

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        totalAmount = totalAmount.plus(transaction.getAmount());
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }
}
