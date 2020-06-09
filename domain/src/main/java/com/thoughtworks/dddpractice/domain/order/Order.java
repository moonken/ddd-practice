package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.domain.order.dto.OrderDTO;
import com.thoughtworks.dddpractice.framework.annotations.domain.AggregateRoot;
import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRoot;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@AggregateRoot
@Getter
public class Order extends BaseAggregateRoot {
  private static final double NO_DISCOUNT = 1;
  private static final double DEFAULT_FREIGHT = 20;

  private String customerId;
  private List<OrderItem> items;
  private double freight;
  private double discount;

  Order(OrderDTO orderDTO, DomainEventPublisher domainEventPublisher) {
    super(domainEventPublisher);
    this.aggregateId = orderDTO.getAggregateId();
    this.customerId = orderDTO.getCustomerId();
    this.discount = orderDTO.getDiscount();
    this.freight = orderDTO.getFreight();
    this.items = orderDTO.getItems().stream().map(OrderItem::new).collect(toList());
  }

  Order(String generatedId, OrderDTO orderVO, DomainEventPublisher domainEventPublisher) {
    this(orderVO, domainEventPublisher);
    this.aggregateId = generatedId;
    this.discount = NO_DISCOUNT;
    this.freight = DEFAULT_FREIGHT;
    this.customerId = orderVO.getCustomerId();
    this.items = orderVO.getItems().stream().map(item -> new OrderItem(UUID.randomUUID().toString(), item)).collect(toList());
  }
}
