package com.str.engg.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.str.engg.model.User;
import com.str.engg.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
   
    @CrossOrigin 
    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user){
    	Random ran = new Random();
    	user.setId(ran.nextLong());
    	return userService.save(user);
    }

}
