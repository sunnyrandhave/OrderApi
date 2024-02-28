package com.OrderApi.orderAPI.entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_sequence", allocationSize = 1)
    private int orderId;
    private Date orderDate = new Date();
    private int userId;
    private int productId;
    private BigDecimal orderPrice;
    private String OrderStatus ;
    private String delivery_Address;

    public Order() {
        orderDate = new Timestamp(orderDate.getTime());
        OrderStatus = "ACCEPTED";
    }

    public String getDelivery_Address() {
        return delivery_Address;
    }

    public void setDelivery_Address(String delivery_Address) {
        this.delivery_Address = delivery_Address;
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

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }


}
