package com.OrderApi.orderAPI.Repositories;

import com.OrderApi.orderAPI.Entities.Order;
import com.OrderApi.orderAPI.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByOrderId(int orderId);
    @Query(value = "SELECT * from shipping_order WHERE user_id = :userId",nativeQuery = true)
    List<Order> findOrdersByUserid(@Param("userId") int userId);


}
