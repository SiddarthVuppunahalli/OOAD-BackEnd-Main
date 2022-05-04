package com.example.stockbroker.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class bankdetails {
    @Id
    private Long accountno;
    private Long routingno;
    private String email;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    private Double balance;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAccountno() {
        return accountno;
    }

    public void setAccountno(Long accountno) {
        this.accountno = accountno;
    }

    public Long getRoutingno() {
        return routingno;
    }

    public void setRoutingno(Long routingno) {
        this.routingno = routingno;
    }
}
