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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int orderId;
    private Date orderDate = new Date();
    private int userId;
    private int productId;
    private int productQuantity;

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

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

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", userId=" + userId +
                ", productId=" + productId +
                ", orderPrice=" + orderPrice +
                ", OrderStatus='" + OrderStatus + '\'' +
                ", delivery_Address='" + delivery_Address + '\'' +
                '}';
    }
}
