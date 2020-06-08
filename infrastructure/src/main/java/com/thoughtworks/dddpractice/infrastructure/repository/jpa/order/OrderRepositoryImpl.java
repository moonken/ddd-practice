package com.thoughtworks.dddpractice.infrastructure.repository.jpa.order;

import com.thoughtworks.dddpractice.domain.order.Order;
import com.thoughtworks.dddpractice.domain.order.OrderFactory;
import com.thoughtworks.dddpractice.domain.order.OrderRepository;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainRepositoryImpl;
import com.thoughtworks.dddpractice.framework.support.domain.GenericDomainRepositoryImpl;
import com.thoughtworks.dddpractice.infrastructure.ObjectMapper;
import org.springframework.context.annotation.Lazy;

@DomainRepositoryImpl
public class OrderRepositoryImpl extends GenericDomainRepositoryImpl<Order, OrderPO> implements OrderRepository {
  private final OrderFactory orderFactory;

  public OrderRepositoryImpl(@Lazy OrderFactory orderFactory) {
    this.orderFactory = orderFactory;
  }

  @Override
  protected Order poToDomain(OrderPO orderPO) {
    return orderFactory.load(ObjectMapper.MAPPER.poToDTO(orderPO));
  }

  @Override
  protected OrderPO domainToPO(Order order) {
    return ObjectMapper.MAPPER.domainToPO(order);
  }
}
