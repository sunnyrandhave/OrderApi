package com.OrderApi.orderAPI.controller;

import com.OrderApi.orderAPI.entities.User;
import com.OrderApi.orderAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/test")
    public String test(){
        return "Welcome to Spring Boot Application";
    }

    @GetMapping("/get/{id}")
    public String getuserbyID(@PathVariable String id){
        return userService.getUserById(Integer.parseInt(id));

    }

    @PostMapping("/createuser")
    public User createuser(@RequestBody User user){
        return userService.createUser(user);
    }


}
