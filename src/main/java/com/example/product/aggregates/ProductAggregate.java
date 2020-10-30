package com.example.product.aggregates;

import com.axon.command.CreateProductCommand;
import com.axon.event.ProductCreatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;


@Data
@NoArgsConstructor
@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private UUID productId;
    private String name;
    private String description;
    private String code;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        AggregateLifecycle.apply(new ProductCreatedEvent(createProductCommand.getProductId(), createProductCommand.getName(), createProductCommand.getDescription(), createProductCommand.getCode()));
    }

    @EventSourcingHandler
    protected void on(ProductCreatedEvent productCreatedEvent) {
        this.productId = productCreatedEvent.getProductId();
        this.name = productCreatedEvent.getName();
        this.description = productCreatedEvent.getDescription();
        this.code = productCreatedEvent.getCode();
    }
}
