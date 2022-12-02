package com.axon.cqrs.commandmodel.order;

import com.axon.cqrs.coreapi.commands.CreateOrderCommand;
import com.axon.cqrs.coreapi.commands.UpdateOrderCommand;
import com.axon.cqrs.coreapi.events.OrderCreatedEvent;
import com.axon.cqrs.coreapi.events.OrderUpdatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;
    private int stock;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command){
        //businessLogic buraya yazilir
        AggregateLifecycle.apply(new OrderCreatedEvent(command.getOrderId()));
    }

    @CommandHandler
    public void handle(UpdateOrderCommand command){
        if (this.stock <= command.getCount()){
            throw new RuntimeException("Stock is not valid");
        }

        AggregateLifecycle.apply(new OrderUpdatedEvent(command.getOrderId(),command.getCount()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event){
        this.orderId = event.getOrderId();
        this.orderConfirmed = false;
        this.stock = 100;
    }

    @EventSourcingHandler
    public void on(OrderUpdatedEvent event){
        this.stock -= event.getCount();
    }
}
