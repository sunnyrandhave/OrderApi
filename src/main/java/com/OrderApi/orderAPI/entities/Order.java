package com.OrderApi.orderAPI.entities;

import com.OrderApi.orderAPI.entities.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private Date orderDate = new Date();
    private int userId;
    private int productId;
    private BigDecimal orderPrice;

    public Order() {
        orderDate = new Timestamp(orderDate.getTime());
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }
}
