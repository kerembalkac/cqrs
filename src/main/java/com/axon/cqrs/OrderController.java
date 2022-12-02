package com.axon.cqrs;


import com.axon.cqrs.coreapi.commands.CreateOrderCommand;
import com.axon.cqrs.coreapi.commands.UpdateOrderCommand;
import com.axon.cqrs.coreapi.queries.FindAllOrderQuery;
import com.axon.cqrs.model.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;


    @PostMapping("/create")
    public String createOrder(){
        String orderId = UUID.randomUUID().toString();
        commandGateway.send(new CreateOrderCommand(orderId));

        /*commandGateway.send(new CreateOrderCommand(orderId))
                .thenCompose(result -> commandGateway.send(new UpdateOrderCommand(orderId,20)));*/

        return orderId;
    }

    @PutMapping("/update/{orderId}/{count}")
    public String updateOrder(@PathVariable String orderId,@PathVariable Integer count){
        commandGateway.send(new UpdateOrderCommand(orderId,count));

        return orderId;
    }

    @GetMapping
    public CompletableFuture<List<OrderEntity>> getAllOrders(){
        return queryGateway.query(new FindAllOrderQuery(), ResponseTypes.multipleInstancesOf(OrderEntity.class));
    }
}
