package com.example.stockbroker.controller;

import com.example.stockbroker.dao.schduleRepository;
import com.example.stockbroker.dao.schedule;
import com.example.stockbroker.dao.userBankRepository;
import com.example.stockbroker.dao.userStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class scheduleController {
    @Autowired
    private userBankRepository bankRepo;

    @Autowired
    private schduleRepository schRepo;

    @Autowired
    private userStockRepository userStocksRepo;

    @RequestMapping(value="getSchedule",method = RequestMethod.POST,produces = {"application/json"})
    public List<schedule> getSchedule(@RequestBody schedule scheduledetails) {
        List<schedule> currentuserschedule=schRepo.findScheduleByEmailAndTicekrsymbol(scheduledetails.getEmail(),scheduledetails.getTicekrsymbol());
        return currentuserschedule;
    }

    @RequestMapping(value="addSchedule",method = RequestMethod.POST,produces = {"application/json"})
    public String addSchedule(@RequestBody schedule scheduledetails) {
        try {

            List<schedule> existingschedule=schRepo.findScheduleByEmailAndTicekrsymbol(scheduledetails.getEmail(),scheduledetails.getTicekrsymbol());
            if(existingschedule!=null)
            {
            schRepo.save(scheduledetails);
            return "Sucesfully added schedule";
        }
        else {return "already exists";}

        } catch (Exception e) {
            return "failed";
        }

        }
    @RequestMapping(value="deleteSchedule",method = RequestMethod.POST,produces = {"application/json"})
    public String deleteSchedule(@RequestBody schedule scheduledetails) {
        try {
            List<schedule> existingschedule=schRepo.findScheduleByEmailAndTicekrsymbol(scheduledetails.getEmail(),scheduledetails.getTicekrsymbol());
                schRepo.delete(existingschedule.get(0));
                return "Sucesfully deleted schedule";
        } catch (Exception e) {
            return "failed";
        }

    }


    }