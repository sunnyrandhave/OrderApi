package com.OrderApi.orderAPI.services;

import com.OrderApi.orderAPI.Repositories.OrderRepository;
import com.OrderApi.orderAPI.Repositories.UserRepository;
import com.OrderApi.orderAPI.entities.Order;
import com.OrderApi.orderAPI.entities.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.transform.sax.SAXResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;
    List<Integer> a;

    public OrderService(List<Integer> a) {
        List<User> userList = userRepository.findAll();
        for(User b : userList){
            a.add(b.getId());
        }
    }

    public String createOrder(Order order){
        if(a.contains(order.getUserId())){
        orderRepository.save(order);
        return "Order Created";
        }else{
            return "Cannot Find User with id : "+order.getUserId();
        }

    }

}
