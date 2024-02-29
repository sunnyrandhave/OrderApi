package com.OrderApi.orderAPI.services;

import com.OrderApi.orderAPI.repositories.OrderRepository;
import com.OrderApi.orderAPI.repositories.ProductRepository;
import com.OrderApi.orderAPI.repositories.UserRepository;
import com.OrderApi.orderAPI.entities.Order;
import com.OrderApi.orderAPI.entities.User;
import com.OrderApi.orderAPI.exception.ProductNotExistsException;
import com.OrderApi.orderAPI.exception.UserNotExistsException;
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

//    public String createOrder(Order order) throws UserNotExistsException {
//        if(userRepository.existsById(order.getUserId()) && productRepository.existsById(order.getProductId())){
//            if(order.getDelivery_Address()==null){
//                User user = userRepository.findById(order.getUserId());
//                order.setDelivery_Address(user.getUserAdress());
//                orderRepository.save(order);
//            }else{
//                orderRepository.save(order);
//            }
//            return "Order Created";
//        }
//        else{
//            return "Validation Error! User or Product may not Exists";
//        }
//    }
    public String createOrder(Order order) throws UserNotExistsException, ProductNotExistsException {
        if(!userRepository.existsById(order.getUserId())){
            throw new UserNotExistsException("User With provided ID doesn't exists!");

        }else if(!productRepository.existsById(order.getProductId())){
            throw new ProductNotExistsException("Product With Provided ID doesn't exists");
        }
        else{
            if(order.getDelivery_Address()==null){
                User user = userRepository.findById(order.getUserId());
                order.setDelivery_Address(user.getUserAdress());
                orderRepository.save(order);
            }else{
                orderRepository.save(order);
            }
            return "Order Created";
        }
        }



    public String updateOrderStatus(@PathVariable String status, @PathVariable int orderId){
        orderRepository.updateOrderStatus(status, orderId);
        return "Order Status Updated";
    }

    public String getOrderById(@PathVariable int id){
        return (orderRepository.getReferenceById(id).toString());
    }



}
