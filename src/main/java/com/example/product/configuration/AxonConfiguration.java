package com.example.product.configuration;

import com.example.product.aggregates.ProductAggregate;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.modelling.command.GenericJpaRepository;
import org.axonframework.modelling.command.Repository;
import org.axonframework.springboot.util.RegisterDefaultEntities;
import org.axonframework.springboot.util.jpa.ContainerManagedEntityManagerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RegisterDefaultEntities(packages = {
        "org.axonframework.eventsourcing.eventstore.jpa"
})
public class AxonConfiguration {


    @Bean
    public EntityManagerProvider entityManagerProvider() {
        return new ContainerManagedEntityManagerProvider();
    }

    @Bean
    public Repository<ProductAggregate> publicationRepository(EntityManagerProvider entityManagerProvider, EventBus eventBus) {
        return GenericJpaRepository.builder(ProductAggregate.class).entityManagerProvider(entityManagerProvider).eventBus(eventBus).build();
    }

    @Bean
    public EventStorageEngine eventStorageEngine(EntityManagerProvider entityManagerProvider, TransactionManager transactionManager) {
        return JpaEventStorageEngine.builder().entityManagerProvider(entityManagerProvider).transactionManager(transactionManager).build();
    }

//    @Bean
//    public Serializer serializer()
//    {
//        return JacksonSerializer.defaultSerializer();
//    }
}
