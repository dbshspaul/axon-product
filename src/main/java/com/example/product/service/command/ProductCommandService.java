package com.example.product.service.command;

import com.example.product.model.Product;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ProductCommandService {
    CompletableFuture<UUID> createProduct(Product product);
}
