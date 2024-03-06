package com.OrderApi.orderAPI.repositories;

import com.OrderApi.orderAPI.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Product p " +
            "SET p.quantity = (SELECT quantity - 1 FROM Product WHERE product_id = 1) " +
            "WHERE p.productId = 1")
    void updateProductQuantity();
}

