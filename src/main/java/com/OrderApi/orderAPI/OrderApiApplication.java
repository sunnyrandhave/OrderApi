package com.OrderApi.orderAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootApplication
public class OrderApiApplication {

	public static void main(String[] args) {
//		SpringApplication.run(OrderApiApplication.class, args);
		BigDecimal a = BigDecimal.valueOf(1.00);
		System.out.println(a);

	}

}
