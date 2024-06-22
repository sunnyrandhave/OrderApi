package com.OrderApi.orderAPI.Entities;

import com.OrderApi.orderAPI.Utilities.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Shipping_order")
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
    private OrderStatus orderStatus;

    @OneToOne
    private Offer promoCode;
    Order() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDate = currentDateTime.format(dateTimeFormatter);
        createdTime = LocalDateTime.parse(formattedDate,dateTimeFormatter);
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
                ", offerApplied" + promoCode +
                '}';
    }
}
