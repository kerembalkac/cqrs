package com.axon.cqrs.repository;

import com.axon.cqrs.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity,String> {
}
