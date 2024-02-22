package com.OrderApi.orderAPI.services;

import com.OrderApi.orderAPI.Repositories.ProductRepository;
import com.OrderApi.orderAPI.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    public ProductRepository productRepository;


    public Product addProduct(Product product){
        productRepository.save(product);
        return product;
    }

    public String getProductById(int id){
        boolean flag = productRepository.existsById(id);
        if(flag){
            return productRepository.findById(id).toString();
        }
        else{
            return "Product Not Found with that ID";
        }
    }

    public String deleteProduct(int id){
        if(productRepository.existsById(id)){
            productRepository.delete(productRepository.getById(id));
            return "Product Deleted";
        }else{
            return "Product Not Found";
        }
    }


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }



}
