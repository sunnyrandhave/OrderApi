package com.OrderApi.orderAPI.controller;

import com.OrderApi.orderAPI.entities.Order;
import com.OrderApi.orderAPI.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/createorder")
    public ResponseEntity<String> createOrder(@RequestBody Order order){
//        orderService.createOrder(order);
        return ResponseEntity.ok().body(orderService.createOrder(order));
    }


    @PostMapping("updatestatus/{orderId}/{orderStatus}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable int orderId,String orderStatus){
        return ResponseEntity.ok().body(orderService.updateStatus(orderId,orderStatus));
    }

}
