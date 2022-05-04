package com.example.stockbroker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface userStockRepository extends JpaRepository<stocks,Integer> {
    stocks findStockByStocktableid(Integer  stockTableId);
    List<stocks> findStockByEmail(String email);
    stocks findStockByTickersymbolAndEmail(String tickersymbol,String email);
}
