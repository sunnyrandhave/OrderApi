package com.OrderApi.orderAPI.Repositories;

import com.OrderApi.orderAPI.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
