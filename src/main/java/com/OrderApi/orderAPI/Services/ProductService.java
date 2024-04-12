package com.OrderApi.orderAPI.Services;

import com.OrderApi.orderAPI.Entities.Product;
import com.OrderApi.orderAPI.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public String addProduct(Product product){
        productRepository.save(product);
        return "Product Added Successfully";
    }

}
