package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.AllArgsConstructor;

import java.util.List;

@DomainFactory
@AllArgsConstructor
public class OrderService {
  private final OrderFactory orderFactory;
  private final OrderRepository orderRepository;
  private final DomainEventPublisher domainEventPublisher;

  public Order create(String customerId, List<OrderItem> items) {
    Order order = orderFactory.create(customerId, items);
    orderRepository.save(order);
    domainEventPublisher.publish(new OrderCreatedEvent(order.getAggregateId()));
    return order;
  }
}
