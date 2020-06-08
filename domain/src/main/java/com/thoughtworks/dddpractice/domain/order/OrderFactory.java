package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.domain.order.dto.OrderDTO;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.AllArgsConstructor;

import java.util.UUID;

@DomainFactory
@AllArgsConstructor
public class OrderFactory {
  private final DomainEventPublisher domainEventPublisher;

  public Order create(OrderDTO orderVO) {
    return new Order(UUID.randomUUID().toString(), orderVO, domainEventPublisher);
  }

  public Order load(OrderDTO orderVO) {
    return new Order(orderVO, domainEventPublisher);
  }
}
