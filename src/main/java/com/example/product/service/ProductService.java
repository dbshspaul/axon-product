package com.example.product.service;

import com.example.product.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Product saveProduct(Product product);
}
