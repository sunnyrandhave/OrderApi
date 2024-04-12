package com.OrderApi.orderAPI.Repositories;

import com.OrderApi.orderAPI.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
