package com.axon.cqrs.query.model;

import com.axon.cqrs.coreapi.events.OrderCreatedEvent;
import com.axon.cqrs.model.OrderEntity;
import com.axon.cqrs.repository.OrderEntityRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("orders")
@RequiredArgsConstructor
public class OrderEventHandler {

    private final OrderEntityRepository orderEntityRepository;

    @EventHandler
    public void on(OrderCreatedEvent event){
        orderEntityRepository.save(new OrderEntity(event.getOrderId(),event.getOrderConfirmed()));
    }
}
