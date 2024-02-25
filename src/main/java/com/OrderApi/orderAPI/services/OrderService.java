package com.OrderApi.orderAPI.services;

import com.OrderApi.orderAPI.Repositories.OrderRepository;
import com.OrderApi.orderAPI.Repositories.UserRepository;
import com.OrderApi.orderAPI.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;


    public String createOrder(Order order){
            orderRepository.save(order);
            return "Order Created";
    }

}
