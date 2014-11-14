package com.visma.spring.model.account;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.*;
import com.visma.spring.DateTimeDeserializer;
import com.visma.spring.DateTimeSerializer;
import com.visma.spring.JodaMoneyDeserializer;
import com.visma.spring.JodaMoneySerializer;
import org.joda.money.Money;
import org.joda.time.DateTime;
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

    public Transaction(){}

    @JsonSerialize(using = JodaMoneySerializer.class)
    public Money getAmount() {
        return amount;
    }

    @JsonDeserialize(using = JodaMoneyDeserializer.class)
    public void setAmount(Money amount) {
        this.amount = amount;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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
