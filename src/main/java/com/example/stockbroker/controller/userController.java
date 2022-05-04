package com.example.stockbroker.controller;

import com.example.stockbroker.dao.userRepository;
import com.example.stockbroker.dao.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class userController {


    @Autowired
    private userRepository newUser;

    @RequestMapping(value = "signUp", method = RequestMethod.POST,produces = {"text/plain"})
    public String addUser(@RequestBody users newUsersRegistration) {
        try {
            List<users> existingUsers = newUser.findUsersByEmail(newUsersRegistration.getEmail());
            if (existingUsers == null || existingUsers.size() == 0) {
                System.out.println("Here");
                newUser.save(newUsersRegistration);
                return "created user";
            }
            else{
                return "user already exists";
            }

            }
            catch (Exception e)
            {
            return e.toString();
            }
    }
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = {"application/json"})
    public HashMap<String,String> getUser(@RequestBody users findUsers) {
        HashMap<String,String> map=new HashMap<String,String>();
        String email= findUsers.getEmail();
        String password= findUsers.getPassword();
        if(newUser.findUsersByEmail(findUsers.getEmail()).size()>0) {
            for (users existingUsers : newUser.findUsersByEmail(findUsers.getEmail())) {
                if (email.equals(existingUsers.getEmail()) && password.equals(existingUsers.getPassword())) {
                    map.put("token", existingUsers.getEmail());
                    map.put("fname", existingUsers.getFirstname());
                    map.put("error", "");
                } else {
                        map.put("fname", "");
                        map.put("token", "");
                        map.put("error", "Wrong Password");
                }
                }
            }
            else
            {
                map.put("fname", "");
                map.put("token", "");
                map.put("error", "No User Found");
    }
    return map;
    }
    @RequestMapping(value = "getUserProfile", method = RequestMethod.POST, produces = {"application/json"})
    public List<users> getProfile(@RequestBody users usersProfile){
        List<users> usersProf = newUser.findUsersByEmail(usersProfile.getEmail());
        return usersProf;
    }

    @RequestMapping(value = "updateUserProfile", method = RequestMethod.POST, produces = {"application/json"})
    public String updateProfile(@RequestBody users usersProfile){
        int existinguserid=0;
        for(users existingUsers :newUser.findUsersByEmail(usersProfile.getEmail()))
        {
            existinguserid= existingUsers.getId();
        }
        try {
            users usersToUpdate =newUser.getOne(existinguserid);
            usersToUpdate.setPassword(usersProfile.getPassword());
            usersToUpdate.setLastname(usersProfile.getLastname());
            usersToUpdate.setFirstname(usersProfile.getFirstname());
            newUser.save(usersToUpdate);
            return "Updated";
        }
        catch (Exception e) {
            return "Update Unsucessful";
        }
    }

    @RequestMapping(value = "forgotPassword", method = RequestMethod.POST, produces = {"application/json"})
    public String forgotPassword(@RequestBody users forgotPassword){
        try {
            String password="";
            String qus="";
            String ans="";
            String usedQuestion=forgotPassword.getQuestion();
            String usedAnswer=forgotPassword.getAnswer();
            for(users existingUsers : newUser.findUsersByEmail(forgotPassword.getEmail()))
            {
                 password=existingUsers.getPassword();
                 qus=existingUsers.getQuestion();
                 ans=existingUsers.getAnswer();
            }
            if(password!=null && password.length()>0 && usedQuestion.equals(qus) && usedAnswer.equals(ans))
            {
                return password;
            }
            else
            {
                return "user not found/wrong answer provided";
            }
        }
        catch (Exception e) {
            return "Failed to find user";
        }
    }

}
