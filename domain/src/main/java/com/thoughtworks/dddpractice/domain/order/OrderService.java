package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.domain.order.dto.OrderDTO;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import lombok.AllArgsConstructor;

@DomainFactory
@AllArgsConstructor
public class OrderService {
  private final OrderFactory orderFactory;
  private final OrderRepository orderRepository;

  public Order create(OrderDTO orderVO) {
    Order order = orderFactory.create(orderVO);
    orderRepository.save(order);
    return order;
  }
}
