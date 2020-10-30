package com.example.product.query;

import com.axon.event.ProductCreatedEvent;
import com.example.product.model.Product;
import com.example.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductProjection {

    private final ProductService productService;

    public ProductProjection(ProductService productService) {
        this.productService = productService;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        log.trace("projecting {}", event);
        Product product = new Product();
        product.setName(event.getName());
        product.setDescription(event.getDescription());
        product.setCode(event.getCode());
        product.setId(event.getProductId());
        productService.saveProduct(product);
    }
}
