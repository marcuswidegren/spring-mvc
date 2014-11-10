package com.visma.spring.model.employee;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee implements Serializable {


    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, length = 500)
    private String name;

    @Column
    private LocalDateTime createdDate;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id; }
    public void nullId() { id = null; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
     
     
}
