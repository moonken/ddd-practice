package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.framework.annotations.domain.DomainService;
import com.thoughtworks.dddpractice.framework.annotations.event.EventListeners;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@DomainService
@AllArgsConstructor
@EventListeners
public class GoodsService {
  private final GoodsFactory goodsFactory;
  private final GoodsRepository goodsRepository;
  private final DomainEventPublisher domainEventPublisher;

  public Goods create(String code, String name, BigDecimal price) {
    Goods goods = goodsFactory.create(code, name, price);
    goodsRepository.save(goods);
    domainEventPublisher.publish(new GoodsCreatedEvent(goods.getAggregateId()));
    return goods;
  }
}
