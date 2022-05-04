package com.example.stockbroker.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class schedule {

    private String email;

    @Id
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String ticekrsymbol;
    private Integer Quantity;
    private String buyorsell;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getAccountno() {
        return accountno;
    }

    public void setAccountno(Long accountno) {
        this.accountno = accountno;
    }

    private String recurringvalue;
    private Long accountno;

    public String getTicekrsymbol() {
        return ticekrsymbol;
    }

    public void setTicekrsymbol(String ticekrsymbol) {
        this.ticekrsymbol = ticekrsymbol;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public String getBuyorsell() {
        return buyorsell;
    }

    public void setBuyorsell(String buyorsell) {
        this.buyorsell = buyorsell;
    }



    public String getRecurringvalue() {
        return recurringvalue;
    }

    public void setRecurringvalue(String recurringvalue) {
        this.recurringvalue = recurringvalue;
    }
}
