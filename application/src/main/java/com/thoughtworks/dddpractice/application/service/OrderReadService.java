package com.thoughtworks.dddpractice.application.service;

import com.thoughtworks.dddpractice.application.exception.ResourceNotFoundException;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.order.OrderPO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.order.JpaOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderReadService {
  private final JpaOrderRepository jpaOrderRepository;

  public OrderPO getPO(String id) {
    return jpaOrderRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Order id " + id + "not found"));
  }

  public List<OrderPO> getAllPO() {
    return jpaOrderRepository.findAll();
  }
}
