package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.service.command.ProductCommandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/product")
public class ProductCommandController {

    private ProductCommandService productCommandService;


    public ProductCommandController(ProductCommandService productCommandService) {
        this.productCommandService = productCommandService;
    }

    @PostMapping
    public CompletableFuture<UUID> createOrder(@RequestBody Product product){
        return productCommandService.createProduct(product);
    }
}
