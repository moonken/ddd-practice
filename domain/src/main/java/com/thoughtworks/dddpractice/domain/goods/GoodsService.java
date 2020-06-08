package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.domain.goods.dto.GoodsDTO;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainService;
import com.thoughtworks.dddpractice.framework.annotations.event.EventListeners;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.AllArgsConstructor;

@DomainService
@AllArgsConstructor
@EventListeners
public class GoodsService {
  private final GoodsFactory goodsFactory;
  private final GoodsRepository goodsRepository;
  private final DomainEventPublisher domainEventPublisher;

  public Goods create(GoodsDTO goodsDTO) {
    Goods goods = goodsFactory.create(goodsDTO);
    goodsRepository.save(goods);
    domainEventPublisher.publish(new GoodsCreatedEvent(goods.getAggregateId()));
    return goods;
  }
}
