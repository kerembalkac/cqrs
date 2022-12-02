package com.axon.cqrs.coreapi.events;

import lombok.Getter;

import java.util.Objects;

@Getter
public class OrderUpdatedEvent {

    private final String orderId;
    private final Integer count;

    public OrderUpdatedEvent(String orderId, Integer count) {
        this.orderId = orderId;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderUpdatedEvent that = (OrderUpdatedEvent) o;
        return Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "OrderUpdatedEvent{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
