package com.example.stockbroker.dao;

public class transfer {
    private Long account1;
    private Long account2;
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getAccount1() {
        return account1;
    }

    public void setAccount1(Long account1) {
        this.account1 = account1;
    }

    public Long getAccount2() {
        return account2;
    }

    public void setAccount2(Long account2) {
        this.account2 = account2;
    }
}
