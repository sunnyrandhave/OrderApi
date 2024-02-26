package com.OrderApi.orderAPI.controller;

import com.OrderApi.orderAPI.entities.Product;
import com.OrderApi.orderAPI.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired


    private ProductService productService;


    @RequestMapping("/test")
    public String gettest(){
        return "working";
    }
    @PostMapping ("/addproduct")
    public Product addProduct(@RequestBody  Product product){
        productService.addProduct(product);
        return product;
    }


    @GetMapping("/getallproducts")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


}
