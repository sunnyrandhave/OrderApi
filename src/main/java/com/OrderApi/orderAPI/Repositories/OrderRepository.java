package com.OrderApi.orderAPI.Repositories;

import com.OrderApi.orderAPI.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
