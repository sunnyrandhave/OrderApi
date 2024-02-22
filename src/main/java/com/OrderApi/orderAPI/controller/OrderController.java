package com.OrderApi.orderAPI.controller;

import com.OrderApi.orderAPI.entities.Order;
import com.OrderApi.orderAPI.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/createorder")
    public ResponseEntity<String> createOrder(@RequestBody Order order){
        if(order.getUserId()==0){
            Order order1 = order;
            return ResponseEntity.internalServerError().body("User Id cant be Zero");
        }
        orderService.createOrder(order);
        return ResponseEntity.ok().body("Order Created Successfully");
    }

}
