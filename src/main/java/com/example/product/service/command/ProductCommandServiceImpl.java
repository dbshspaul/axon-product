package com.example.product.service.command;

import com.axon.command.CreateProductCommand;
import com.example.product.model.Product;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {
    private final CommandGateway commandGateway;

    public ProductCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<UUID> createProduct(Product product) {
        return commandGateway.send(
                new CreateProductCommand(UUID.randomUUID(), product.getName(), product.getDescription(), product.getCode())
        );
    }
}
