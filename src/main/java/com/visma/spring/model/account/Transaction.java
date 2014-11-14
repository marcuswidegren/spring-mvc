package com.visma.spring.model.account;

import org.joda.money.Money;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "account_transaction")
public class Transaction implements Serializable, Comparable<Transaction> {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Money amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    protected Transaction(){}

    public Transaction(long id, Money amount, LocalDateTime timestamp) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Money getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object secondObject) {
        if(!(secondObject instanceof Transaction)) {
            return false;
        }
        Transaction secondTransaction = (Transaction) secondObject;
        return  Objects.equals(id, secondTransaction.getId()) &&
                Objects.equals(timestamp, secondTransaction.getTimestamp()) &&
                Objects.equals(amount, secondTransaction.getAmount());
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = result * 31 + timestamp.hashCode();
        result = result * 31 + amount.hashCode();
        return result;
    }

    @Override
    public int compareTo(Transaction o) {
        return timestamp.compareTo(o.getTimestamp());
    }
}
