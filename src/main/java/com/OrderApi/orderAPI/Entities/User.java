package com.OrderApi.orderAPI.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;

    @Column(unique = true)
    private String userNumber;

    private String userAddress;

}
