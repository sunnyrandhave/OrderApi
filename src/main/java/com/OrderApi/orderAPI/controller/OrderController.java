package com.OrderApi.orderAPI.controller;

import com.OrderApi.orderAPI.entities.Order;
import com.OrderApi.orderAPI.exception.OrderNotExistsException;
import com.OrderApi.orderAPI.exception.ProductNotAvailableException;
import com.OrderApi.orderAPI.exception.ProductNotExistsException;
import com.OrderApi.orderAPI.exception.UserNotExistsException;
import com.OrderApi.orderAPI.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) throws UserNotExistsException, ProductNotExistsException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
        } catch (UserNotExistsException | ProductNotExistsException | ProductNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("updatestatus/{orderId}/{orderStatus}")
    public ResponseEntity<String> updateOrderStatus(@RequestParam String orderStatus,@RequestParam int  orderId) throws OrderNotExistsException {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderStatus(orderStatus,orderId));
        }catch(OrderNotExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("get/{id}")
    public ResponseEntity<String> getOrderById(@RequestParam int id) throws OrderNotExistsException{
        try{
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
        }catch(OrderNotExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("getAll")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

}
