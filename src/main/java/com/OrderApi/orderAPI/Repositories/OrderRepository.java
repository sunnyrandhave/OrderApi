package com.OrderApi.orderAPI.Repositories;

import com.OrderApi.orderAPI.Entities.Order;
import com.OrderApi.orderAPI.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByOrderId(int orderId);
}
