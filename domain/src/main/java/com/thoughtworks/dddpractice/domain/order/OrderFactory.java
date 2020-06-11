package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.domain.goods.GoodsRepository;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@DomainFactory
@AllArgsConstructor
public class OrderFactory {
  private final GoodsRepository goodsRepository;

  public Order create(String customerId, List<OrderItem> items) {
    items
      .forEach(orderItem -> orderItem.setGoods(new GoodsSnapshot(goodsRepository.load(orderItem.getGoodsId()))));
    return new Order(customerId, items);
  }

  public Order load(String aggregateId, String customerId, List<OrderItem> items, BigDecimal freight, double discount,
                    BigDecimal totalAmount) {
    return new Order(aggregateId, customerId, items, freight, discount, totalAmount);
  }
}
