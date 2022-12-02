package com.axon.cqrs.query.model;

import com.axon.cqrs.coreapi.events.OrderCreatedEvent;
import com.axon.cqrs.coreapi.queries.FindAllOrderQuery;
import com.axon.cqrs.model.OrderEntity;
import com.axon.cqrs.repository.OrderEntityRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ProcessingGroup("orders")
@RequiredArgsConstructor
public class OrderEventHandler {

    private final OrderEntityRepository orderEntityRepository;

    @EventHandler
    public void on(OrderCreatedEvent event){
        orderEntityRepository.save(new OrderEntity(event.getOrderId(),event.getOrderConfirmed()));
    }

    @QueryHandler
    public List<OrderEntity> on(FindAllOrderQuery query){
        return orderEntityRepository.findAll();
    }
}
