package com.OrderApi.orderAPI.Repositories;

import com.OrderApi.orderAPI.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
