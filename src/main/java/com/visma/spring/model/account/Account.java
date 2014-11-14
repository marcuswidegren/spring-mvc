package com.visma.spring.model.account;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.visma.spring.JodaMoneyDeserializer;
import com.visma.spring.JodaMoneySerializer;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
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

    @JsonSerialize(using = JodaMoneySerializer.class)
    public Money getAmount() {
        return totalAmount;
    }

    @JsonDeserialize(using = JodaMoneyDeserializer.class)
    public void setAmount(Money amount) {
        this.totalAmount = amount;
    }

    public Account(Long id) {
        this.id = id;
    }

    public Account(){} // for hibernate

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

    @JsonDeserialize
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }


}
