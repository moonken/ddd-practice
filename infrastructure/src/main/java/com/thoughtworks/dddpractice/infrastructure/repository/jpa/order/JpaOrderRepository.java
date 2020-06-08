package com.thoughtworks.dddpractice.infrastructure.repository.jpa.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaOrderRepository extends JpaRepository<OrderPO, String> {
}
