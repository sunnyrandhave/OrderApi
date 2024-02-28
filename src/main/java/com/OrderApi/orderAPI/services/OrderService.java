package com.OrderApi.orderAPI.services;

import com.OrderApi.orderAPI.Repositories.OrderRepository;
import com.OrderApi.orderAPI.Repositories.ProductRepository;
import com.OrderApi.orderAPI.Repositories.UserRepository;
import com.OrderApi.orderAPI.entities.Order;
import com.OrderApi.orderAPI.entities.User;
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
            if(order.getDelivery_Address()==null){
                int id = order.getUserId();
                User user = userRepository.findById(order.getUserId());
                order.setDelivery_Address(user.getUserAdress());
                orderRepository.save(order);
            }else{
                orderRepository.save(order);
            }
            return "Order Created";
        }
        else{
            return "Validation Error User or Product may not Exists";
        }
    }

    public String updateOrderStatus(@PathVariable String status, @PathVariable int orderId){
        orderRepository.updateOrderStatus(status, orderId);
        return "Order Status Updated";
    }

    public String getOrderById(@PathVariable int id){
        return (orderRepository.getReferenceById(id)).toString();
    }



}
