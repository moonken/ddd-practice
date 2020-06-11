package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.framework.annotations.domain.AggregateRoot;
import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRoot;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AggregateRoot
@Getter
public class Order extends BaseAggregateRoot {
  private static final double NO_DISCOUNT = 1;
  private static final BigDecimal DEFAULT_FREIGHT = BigDecimal.valueOf(20);

  private String aggregateId;
  private String customerId;
  private List<OrderItem> items;
  private BigDecimal freight;
  private double discount;
  private BigDecimal totalAmount;

  Order(String aggregateId, String customerId, List<OrderItem> items, BigDecimal freight, double discount,
        BigDecimal totalAmount) {
    this.aggregateId = aggregateId;
    this.customerId = customerId;
    this.items = items;
    this.freight = freight;
    this.discount = discount;
    this.totalAmount = totalAmount;
  }

  Order(String customerId, List<OrderItem> items) {
    this.aggregateId = UUID.randomUUID().toString();
    this.freight = DEFAULT_FREIGHT;
    this.discount = NO_DISCOUNT;
    this.customerId = customerId;
    this.items = items;
    calcTotalAmount();
  }

  private BigDecimal calcTotalAmount() {
    this.totalAmount =
      this.items.stream().map(OrderItem::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add).add(freight);
    return totalAmount;
  }
}
