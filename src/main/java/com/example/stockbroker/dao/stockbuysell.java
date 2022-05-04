package com.example.stockbroker.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

public class stockbuysell {



    private Integer stocktableid;

    private Integer quantity;

    private Long accountno;

    public Double getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(Double currentprice) {
        this.currentprice = currentprice;
    }

    private String email;
    
   private String stockname;

   private Double currentprice;

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public String getTickersymbol() {
        return tickersymbol;
    }

    public void setTickersymbol(String tickersymbol) {
        this.tickersymbol = tickersymbol;
    }

    private String tickersymbol;










    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }










    public Integer getStocktableid() {
        return stocktableid;
    }

    public void setStocktableid(Integer stocktableid) {
        this.stocktableid = stocktableid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Long getAccountno() {
        return accountno;
    }

    public void setAccountno(Long accountno) {
        this.accountno = accountno;
    }
}
