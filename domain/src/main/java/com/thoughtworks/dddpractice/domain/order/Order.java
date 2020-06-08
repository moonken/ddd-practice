package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.domain.order.dto.OrderDTO;
import com.thoughtworks.dddpractice.framework.annotations.domain.AggregateRoot;
import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRoot;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@AggregateRoot
@Getter
public class Order extends BaseAggregateRoot {
  private String customerId;
  private List<OrderItem> items;

  Order(OrderDTO orderVO, DomainEventPublisher domainEventPublisher) {
    super(domainEventPublisher);
    this.aggregateId = orderVO.getAggregateId();
    this.customerId = orderVO.getCustomerId();
    this.items = orderVO.getItems().stream().map(OrderItem::new).collect(toList());
  }

  Order(String generatedId, OrderDTO orderVO, DomainEventPublisher domainEventPublisher) {
    this(orderVO, domainEventPublisher);
    this.aggregateId = generatedId;
    this.customerId = orderVO.getCustomerId();
    this.items = orderVO.getItems().stream().map(item -> new OrderItem(UUID.randomUUID().toString(), item)).collect(toList());
  }
}
