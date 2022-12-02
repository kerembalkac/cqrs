package com.axon.cqrs.coreapi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

@Getter
public class UpdateOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
    private final Integer count;

    public UpdateOrderCommand(String orderId, Integer count) {
        this.orderId = orderId;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateOrderCommand that = (UpdateOrderCommand) o;
        return Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "UpdateOrderCommand{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
