package com.OrderApi.orderAPI.controller;

import com.OrderApi.orderAPI.entities.User;
import com.OrderApi.orderAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("createuser")
    public ResponseEntity<String> createUser(@RequestBody User user){
        try {
            return userService.createUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
