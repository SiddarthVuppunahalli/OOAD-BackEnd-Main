package com.example.stockbroker.controller;
import com.example.stockbroker.dao.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/")
public class stockController {

    @Autowired
    private userStockRepository userStocksRepo;

    @Autowired
    private userBankRepository bankRep;

    @RequestMapping(value="getUserStocks",method = RequestMethod.POST,produces = {"application/json"})
    public List<stocks> getStocks(@RequestBody stocks stockData) {
        List<stocks> getuserlist=new ArrayList<stocks>();
        for(stocks userStockData : userStocksRepo.findStockByEmail(stockData.getEmail()))
        {
            userStockData.setCurrentprice(getCurrentStockPrice(userStockData.getTickersymbol()));
            getuserlist.add(userStockData);
        }
    return getuserlist;

    }

    @RequestMapping(value="buyCurrentStocks",method = RequestMethod.POST)
    public String buyStocks(@RequestBody stockbuysell stockbuyselldetails) {
        try {
            stocks stockData = new stocks();
            stockData=userStocksRepo.findStockByStocktableid(stockbuyselldetails.getStocktableid());
            for (bankdetails userBankData : bankRep.findBankDetailsByAccountno(stockbuyselldetails.getAccountno()))
            {
                Integer newQuantity = stockData.getQuantity() + stockbuyselldetails.getQuantity();
                Double stockprice =stockbuyselldetails.getCurrentprice();
                //Double stockprice = 10.0;
                if (userBankData.getBalance() >= (stockbuyselldetails.getQuantity() * stockprice)) {
                    userBankData.setBalance(userBankData.getBalance() - (stockbuyselldetails.getQuantity() * stockprice));
                    stockData.setQuantity(newQuantity);
                    userStocksRepo.save(stockData);
                    bankRep.save(userBankData);
                    return "Sucessefully Bought Stocks";
                } else {
                    return "Not enough Funds";
                }
            }
            return "no account exists,please try again";
        }

        catch (Exception e){
            return e.toString();
        }

    }
    @RequestMapping(value="sellCurrentStocks",method = RequestMethod.POST,produces = {"application/json"})
    public String sellStocks(@RequestBody stockbuysell stockbuyselldetails) {
        try {
            stocks stockData = new stocks();
            stockData = userStocksRepo.findStockByStocktableid(stockbuyselldetails.getStocktableid());
            Integer newQuantity = stockData.getQuantity() - stockbuyselldetails.getQuantity();
            for (bankdetails userBankData : bankRep.findBankDetailsByAccountno(stockbuyselldetails.getAccountno())) {
                Double stockprice = stockbuyselldetails.getCurrentprice();
                userBankData.setBalance(userBankData.getBalance() + (stockbuyselldetails.getQuantity() * stockprice));
                stockData.setQuantity(newQuantity);
                if (newQuantity <= 0) {
                    userStocksRepo.delete(stockData);
                    return "sucessfully deleted stocks";
                }
                    userStocksRepo.save(stockData);
                    bankRep.save(userBankData);
            }
            return "Sucessefully sold Stocks";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @RequestMapping(value="updateUserStocks",method = RequestMethod.POST,produces = {"application/json"})
    public String updateStocks(@RequestBody stocks stockData) {
        try {
            if (stockData.getQuantity() == 0) {
                userStocksRepo.delete(stockData);

            } else {
                userStocksRepo.save(stockData);
            }
            return "sucessful";
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }

    @RequestMapping(value="buyNewStock",method = RequestMethod.POST,produces = {"application/json"})
    public String buyNewStock(@RequestBody stockbuysell stockbuyselldetails) {
        String message="";
        try {
            String tickerSymbol=stockbuyselldetails.getTickersymbol();
            String stockName=stockbuyselldetails.getStockname();
            Integer quantity= stockbuyselldetails.getQuantity();
            Long accountno=stockbuyselldetails.getAccountno();
            String email=stockbuyselldetails.getEmail();

            stocks existingStocks=userStocksRepo.findStockByTickersymbolAndEmail(tickerSymbol,email);
            if(existingStocks==null) {
                stocks newStockEntry = new stocks();

                        Double currentPrice = stockbuyselldetails.getCurrentprice();
                        for (bankdetails userBankData : bankRep.findBankDetailsByAccountno(accountno)) {
                            if (userBankData.getBalance() > (quantity * currentPrice)) {
                                newStockEntry.setEmail(email);
                                newStockEntry.setQuantity(quantity);
                                newStockEntry.setStockname(stockName);
                                newStockEntry.setTickersymbol(tickerSymbol);
                                userBankData.setBalance(userBankData.getBalance() - (quantity * currentPrice));
                                userStocksRepo.save(newStockEntry);
                                bankRep.save(userBankData);
                                message = "Sucessfully bought new stocks";
                            } else {
                                message = "not enough balance in this account";
                            }
                        }
                    }
            else{
                Integer id=existingStocks.getStocktableid();

                stockbuysell appendData=new stockbuysell();
                appendData.setStocktableid(id);
                appendData.setAccountno(accountno);
                appendData.setQuantity(quantity);
                message=buyStocks(appendData);

            }
            return message;
        }
        catch (Exception e) {
            return "error please try again";
        }
    }

    public static Double getCurrentStockPrice(String tickerSymbol)
    {

        final String uri = "http://localhost:8081/getstocks";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("tickerSymbol", tickerSymbol);
        params.put("range","current");
        List<Double> currentprice=restTemplate.postForObject(uri,params,List.class);
        return currentprice.get(0);
    }
    @RequestMapping(value="getAllStocks",method = RequestMethod.GET,produces = {"application/json"})
    public static List<stocks> getAllStocks()
    {
        final String uri = "http://localhost:8081/getAllStocks";
        RestTemplate restTemplate = new RestTemplate();
        List<stocks> allStocks=restTemplate.getForObject(uri,List.class);
        return allStocks;
    }

    @RequestMapping(value="getStockHistory",method = RequestMethod.POST,produces = {"application/json"})
    public static List<Double> getHistory(@RequestBody history gethistory)
    {
        final String uri = "http://localhost:8081/getstocks";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("tickerSymbol", gethistory.getTickerSymbol());
        params.put("range",gethistory.getRange());
        List<Double> allStocks=restTemplate.postForObject(uri,params,List.class);
        return allStocks;
    }
}
