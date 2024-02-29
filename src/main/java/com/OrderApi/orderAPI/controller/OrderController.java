package com.OrderApi.orderAPI.controller;

import com.OrderApi.orderAPI.entities.Order;
import com.OrderApi.orderAPI.exception.ProductNotExistsException;
import com.OrderApi.orderAPI.exception.UserNotExistsException;
import com.OrderApi.orderAPI.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) throws UserNotExistsException, ProductNotExistsException {
        try {
            return ResponseEntity.ok().body(orderService.createOrder(order));
        } catch (UserNotExistsException | ProductNotExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("updatestatus/{orderId}/{orderStatus}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable int orderId,@PathVariable String orderStatus){
        return ResponseEntity.ok().body(orderService.updateOrderStatus(orderStatus,orderId));
    }


    @GetMapping("get/{id}")
    public String getOrderById(@PathVariable int id){
        return orderService.getOrderById(id);
    }


}
