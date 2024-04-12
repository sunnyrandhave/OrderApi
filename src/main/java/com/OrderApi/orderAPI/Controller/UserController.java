package com.OrderApi.orderAPI.Controller;

import com.OrderApi.orderAPI.Entities.User;
import com.OrderApi.orderAPI.Exceptions.InvalidPhoneNumberException;
import com.OrderApi.orderAPI.Exceptions.UserNumberAlreadyExistsException;
import com.OrderApi.orderAPI.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping("/test")
    public ResponseEntity<String> testController(){
        return new ResponseEntity<>("API Working!",HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) throws UserNumberAlreadyExistsException, InvalidPhoneNumberException {
        return new ResponseEntity<>(userServices.createUser(user),HttpStatus.CREATED);
    }








}
