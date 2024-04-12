package com.OrderApi.orderAPI.Controller;

import com.OrderApi.orderAPI.Entities.Product;
import com.OrderApi.orderAPI.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addproduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }


}
