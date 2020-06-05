package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.domain.goods.exception.GoodsCodeDuplicatedException;
import com.thoughtworks.dddpractice.domain.goods.vo.GoodsVO;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariant;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariants;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import java.util.UUID;

@Invariants({
  "duplicates: code can not duplicates",
})
@DomainFactory
@AllArgsConstructor
public class GoodsFactory {

  private final DomainEventPublisher domainEventPublisher;
  private final GoodsRepository goodsRepository;
  private final AutowireCapableBeanFactory autowireCapableBeanFactory;

  public Goods create(GoodsVO goodsVO) {
    Goods goods = new Goods(UUID.randomUUID().toString(), goodsVO);
    autowireCapableBeanFactory.autowireBean(goods);
    goodsRepository.save(goods);
    domainEventPublisher.publish(new GoodsCreatedEvent(goods.getAggregateId()));
    return goods;
  }

  public Goods load(GoodsVO goodsVO) {
    Goods goods = new Goods(goodsVO);
    autowireCapableBeanFactory.autowireBean(goods);
    return goods;
  }

  @Invariant("duplicates")
  private void validateCodeDuplicated(String code) {
    if (goodsRepository.findByCode(code).isPresent()) {
      throw new GoodsCodeDuplicatedException(code);
    }
  }

}
