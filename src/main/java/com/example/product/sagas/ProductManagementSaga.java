package com.example.product.sagas;

import com.axon.command.CreateInventoryCommand;
import com.axon.event.InventoryCreatedEvent;
import com.axon.event.ProductCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class ProductManagementSaga {

    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "productId")
    public void handle(ProductCreatedEvent productCreatedEvent) {
        UUID inventoryId = UUID.randomUUID();
        System.out.println("Saga invoked");

        //associate Saga
        SagaLifecycle.associateWith("inventoryId", inventoryId.toString());

        System.out.println("product code: " + productCreatedEvent.getCode());

        //send the commands
        commandGateway.send(new CreateInventoryCommand(inventoryId, productCreatedEvent.getCode(), 1L));
    }

    @SagaEventHandler(associationProperty = "inventoryId")
    public void handle(InventoryCreatedEvent inventoryCreatedEvent){
        System.out.println("Saga end");
        SagaLifecycle.end();
    }
}
