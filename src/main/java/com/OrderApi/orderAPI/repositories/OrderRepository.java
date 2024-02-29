package com.OrderApi.orderAPI.repositories;

import com.OrderApi.orderAPI.entities.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query(value = "UPDATE order_tb SET order_status = ?1 where order_id = ?2",nativeQuery = true)
    @Modifying
    @Transactional
    public void updateOrderStatus(String status,int id);
}
