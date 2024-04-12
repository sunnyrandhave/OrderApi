package com.OrderApi.orderAPI.Services;

import com.OrderApi.orderAPI.Entities.Order;
import com.OrderApi.orderAPI.Entities.Product;
import com.OrderApi.orderAPI.Utilities.Status;
import com.OrderApi.orderAPI.Entities.User;
import com.OrderApi.orderAPI.Repositories.OrderRepository;
import com.OrderApi.orderAPI.Repositories.UserRepository;
import com.OrderApi.orderAPI.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;


    public String createOrder(Order order) throws Exception {
        Optional<User>  userOptional = userRepository.findById(order.getUserId());
        Optional<Product> productOptional = productRepository.findById(order.getProductId());
        if(userOptional.isPresent() && productOptional.isPresent()){
            if(order.getDeliveryAddress()==null){
                order.setDeliveryAddress(userOptional.get().getUserAddress());
            }
            order.setOrderStatus(Status.PENDING);
            order.setCustomerName(userOptional.get().getUserName());
            order.setOrderValue(productOptional.get().getProductValue());
            order.setOrderValue(productOptional.get().getProductValue().multiply(BigDecimal.valueOf(order.getProductQuantity())));
            orderRepository.save(order);
            return order.toString();
        }else{
            throw new Exception("User Id or Product Id may not exist");
        }
    }

    public String getOrderById(int orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()){
            return order.toString();
        }else{
            throw new Exception("Order Id doesn't Exists");
        }
    }





}
