package com.example.stockbroker.controller;

import com.example.stockbroker.dao.bankdetails;
import com.example.stockbroker.dao.stocks;
import com.example.stockbroker.dao.transfer;
import com.example.stockbroker.dao.userBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class bankController {
    @Autowired
    private userBankRepository bankRepo;

    @RequestMapping(value = "addBankDetails", method = RequestMethod.POST, produces = {"application/json"})
    public String addBank(@RequestBody bankdetails bankData) {
        try {
            if(bankData.getBalance()==null)
            {
                bankData.setBalance(50000.0);;
            }
            bankRepo.save(bankData);
            return "Bank Details Saved Successfully";
        }
        catch (Exception e)
        {
            return "Bank Details Already Exist";
        }
    }
    @RequestMapping(value="getBankDetails",method = RequestMethod.POST, produces = {"application/json"})
    public List<bankdetails> getbank(@RequestBody bankdetails bankData){
        List<bankdetails> userBankData = bankRepo.findBankDetailsByEmail(bankData.getEmail());
        return userBankData;
    }
    @RequestMapping(value="deleteBankDetails",method = RequestMethod.POST, produces = {"application/json"})
    public String deletebank(@RequestBody bankdetails bankData){
        try{
            bankRepo.delete(bankData);
            return "Deleted Successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @RequestMapping(value="transferBankFunds",method = RequestMethod.POST, produces = {"application/json"})
    public String tranferFunds(@RequestBody transfer tranferData){
        try{
            bankdetails bank1 = new bankdetails();
            bankdetails bank2=new bankdetails();
           for(bankdetails bank: bankRepo.findBankDetailsByAccountno(tranferData.getAccount1()))
           {
               bank1=bank;
           }
            for(bankdetails bank: bankRepo.findBankDetailsByAccountno(tranferData.getAccount2()))
            {
                bank2=bank;
            }
            Double Amount=tranferData.getAmount();
            Double oldb1balance=bank1.getBalance();
            Double oldb2balance=bank2.getBalance();
            bank1.setBalance(oldb1balance-Amount);
            bank2.setBalance(oldb2balance+Amount);
            bankRepo.save(bank1);
            bankRepo.save(bank2);
            return "Sucessfully Transfered funds";

        }
        catch (Exception e){
            return "Please try again";
        }
    }



}
