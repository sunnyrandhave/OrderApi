package com.OrderApi.orderAPI.Services;

import com.OrderApi.orderAPI.Entities.Order;
import com.OrderApi.orderAPI.Entities.Product;
import com.OrderApi.orderAPI.Exceptions.InvalidOrderStatusException;
import com.OrderApi.orderAPI.Exceptions.MaximumOrderValueException;
import com.OrderApi.orderAPI.Exceptions.MinimumOrderValueException;
import com.OrderApi.orderAPI.Exceptions.OrderNotFoundException;
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
            order.setOrderValue(productOptional.get().getProductValue().multiply(BigDecimal.valueOf(order.getProductQuantity())));
            if(order.getOrderValue().intValue()<99){
                throw new MinimumOrderValueException("Minimum order value Should More Than Rs 99");
            }
            else if(order.getOrderValue().intValue()>=5000){
                throw new MaximumOrderValueException("Maximum order value Should be Less Than Rs 5000");
            }
            orderRepository.save(order);
            return order.toString();
        }else{
            throw new Exception("User Id or Product Id may not exist");
        }
    }

    public String getOrderById(int orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()){
            return order.get().toString();
        }else{
            throw new OrderNotFoundException("Order Id doesn't Exists");
        }
    }

    public String updateOrderStatus(int orderID, String orderStatus) throws OrderNotFoundException, InvalidOrderStatusException {
        Optional<Order> optionalOrder = orderRepository.findByOrderId(orderID);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setCustomerName("DELIVERED");
            orderRepository.saveAndFlush(order);
            return order.toString();
        }else{
            throw new OrderNotFoundException("Order not Found");
        }

    }

}
