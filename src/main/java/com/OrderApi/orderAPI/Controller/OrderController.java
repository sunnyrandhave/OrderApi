package com.OrderApi.orderAPI.Controller;

import com.OrderApi.orderAPI.Entities.Order;
import com.OrderApi.orderAPI.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) throws Exception {
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }
    @GetMapping("/get/{orderId}")
    public ResponseEntity<String> getOrderById(@PathVariable int orderId) throws Exception {
        return new ResponseEntity<>(orderService.getOrderById(orderId),HttpStatus.FOUND);
    }

    @PutMapping("/update/{orderId}/{orderStatus}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable("orderId")int orderId,@PathVariable("orderStatus") String orderStatus) throws  Exception {
        return new ResponseEntity<>(orderService.updateOrderStatus(orderId, orderStatus),HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<String>> getAllOrder() throws Exception {
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.FOUND);
    }



}
