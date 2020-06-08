package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.domain.goods.exception.GoodsCodeDuplicatedException;
import com.thoughtworks.dddpractice.domain.goods.vo.GoodsVO;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariant;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariants;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Invariants({
  "duplicates: code can not duplicates",
})
@DomainFactory
@AllArgsConstructor
public class GoodsFactory {

  private final DomainEventPublisher domainEventPublisher;
  private final GoodsRepository goodsRepository;

  @Invariant("duplicates")
  public Goods create(GoodsVO goodsVO) {
    if (goodsRepository.findByCode(goodsVO.getCode()).isPresent()) {
      throw new GoodsCodeDuplicatedException(goodsVO.getCode());
    }

    Goods goods = new Goods(UUID.randomUUID().toString(), goodsVO, domainEventPublisher);
    goodsRepository.save(goods);
    domainEventPublisher.publish(new GoodsCreatedEvent(goods.getAggregateId()));
    return goods;
  }

  public Goods load(GoodsVO goodsVO) {
    return new Goods(goodsVO, domainEventPublisher);
  }

}
