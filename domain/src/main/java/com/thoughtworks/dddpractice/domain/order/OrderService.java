package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.domain.order.dto.OrderDTO;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.AllArgsConstructor;

@DomainFactory
@AllArgsConstructor
public class OrderService {
  private final OrderFactory orderFactory;
  private final OrderRepository orderRepository;
  private final DomainEventPublisher domainEventPublisher;

  public Order create(OrderDTO orderVO) {
    Order order = orderFactory.create(orderVO);
    orderRepository.save(order);
    domainEventPublisher.publish(new OrderCreatedEvent(order.getAggregateId()));
    return order;
  }
}
