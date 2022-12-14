package com.axon.cqrs.coreapi.events;

import com.axon.cqrs.coreapi.commands.CreateOrderCommand;
import lombok.Getter;

import java.util.Objects;
@Getter
public class OrderCreatedEvent {

    private final String orderId;
    private final Boolean orderConfirmed;

    public OrderCreatedEvent(String orderId,Boolean orderConfirmed) {

        this.orderId = orderId;
        this.orderConfirmed = orderConfirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCreatedEvent that = (OrderCreatedEvent) o;
        return Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
