package com.OrderApi.orderAPI.services;

import com.OrderApi.orderAPI.Repositories.OrderRepository;
import com.OrderApi.orderAPI.Repositories.ProductRepository;
import com.OrderApi.orderAPI.Repositories.UserRepository;
import com.OrderApi.orderAPI.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    public String createOrder(Order order){
        if(userRepository.existsById(order.getUserId()) && productRepository.existsById(order.getProductId())){
            orderRepository.save(order);
            return "Order Created";
        }
        else{
            return "Validation Error User or Product may not Exists";
        }
    }
    public String updateStatus(@PathVariable int orderId,String status){
        Boolean flag = orderRepository.existsById(orderId);
        if(flag) {
            orderRepository.getReferenceById(orderId).setOrderStatus(status);
//            order.setOrderStatus(status);
            return "Order Status Updated";
        }else{
            return "Order Not Exists!";
        }
    }



}
