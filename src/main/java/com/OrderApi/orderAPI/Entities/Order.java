package com.OrderApi.orderAPI.Entities;

import com.OrderApi.orderAPI.Utilities.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "order_tb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private LocalDateTime createdTime;
    private int userId;
    private String customerName;
    private String deliveryAddress;
    private int productId;
    private int productQuantity;
    private BigDecimal orderValue;

    @Enumerated(EnumType.STRING)
    public Status orderStatus;
//    private String orderStatus;
    Order(){
        createdTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", createdTime=" + createdTime +
                ", customerName='" + customerName + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", orderValue=" + orderValue +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
