package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.domain.order.dto.OrderDTO;
import com.thoughtworks.dddpractice.framework.annotations.domain.AggregateRoot;
import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRoot;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@AggregateRoot
@Getter
public class Order extends BaseAggregateRoot {
  private static final double NO_DISCOUNT = 1;
  private static final BigDecimal DEFAULT_FREIGHT = BigDecimal.valueOf(20);

  private String customerId;
  private List<OrderItem> items;
  private BigDecimal freight;
  private double discount;
  private BigDecimal totalAmount;

  Order(OrderDTO orderDTO) {
    this.aggregateId = orderDTO.getAggregateId();
    this.customerId = orderDTO.getCustomerId();
    this.discount = orderDTO.getDiscount();
    this.freight = orderDTO.getFreight();
    this.items = orderDTO.getItems().stream().map(OrderItem::new).collect(toList());
    this.totalAmount = orderDTO.getTotalAmount();
  }

  Order(String generatedId, OrderDTO orderVO) {
    this(orderVO);
    this.aggregateId = generatedId;
    this.discount = NO_DISCOUNT;
    this.freight = DEFAULT_FREIGHT;
    this.customerId = orderVO.getCustomerId();
    this.items = orderVO.getItems().stream().map(item -> new OrderItem(UUID.randomUUID().toString(), item)).collect(toList());
    calcTotalAmount();
  }

  private BigDecimal calcTotalAmount() {
    this.totalAmount =
      this.items.stream().map(OrderItem::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add).add(freight);
    return totalAmount;
  }
}
