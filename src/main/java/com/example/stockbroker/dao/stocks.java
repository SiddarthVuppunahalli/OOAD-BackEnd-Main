package com.example.stockbroker.dao;



import javax.persistence.*;

@Entity
public class stocks {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer stocktableid;
    private String email;
    private String tickersymbol;
    private String stockname;

    public Double getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(Double currentprice) {
        this.currentprice = currentprice;
    }

    private Integer quantity;
    private Double currentprice;

    public Integer getStocktableid() {
        return stocktableid;
    }

    public void setStocktableid(Integer stocktableid) {
        this.stocktableid = stocktableid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTickersymbol() {
        return tickersymbol;
    }

    public void setTickersymbol(String tickersymbol) {
        this.tickersymbol = tickersymbol;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }




}
