package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariant;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariants;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import com.thoughtworks.dddpractice.domain.goods.exception.GoodsCodeDuplicatedException;
import com.thoughtworks.dddpractice.domain.goods.exception.GoodsNameTooLongException;
import com.thoughtworks.dddpractice.domain.goods.exception.PriceLessThanZeroException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

@Invariants({
  "name_length_limit: name can not too long",
  "duplicates: code can not duplicates",
  "price_must_great_than_0: price must great than 0"
})
@DomainFactory
@AllArgsConstructor
public class GoodsFactory {
  public static final int MAX_GOODS_NAME = 20;
  private final DomainEventPublisher domainEventPublisher;
  private final GoodsRepository goodsRepository;
  private final AutowireCapableBeanFactory autowireCapableBeanFactory;

  public Goods create(String code, String name, Double price) {
    validateNameLength(name);
    validatePrice(price);
    validateCodeDuplicated(code);

    Goods goods = new Goods(code, name, price);
    autowireCapableBeanFactory.autowireBean(goods);
    goodsRepository.save(goods);
    domainEventPublisher.publish(new GoodsCreatedEvent(goods.getAggregateId()));
    return goods;
  }

  @Invariant("duplicates")
  private void validateCodeDuplicated(String code) {
    if (goodsRepository.findByCode(code).isPresent()) {
      throw new GoodsCodeDuplicatedException(code);
    }
  }

  @Invariant("price_must_great_than_0")
  private void validatePrice(Double price) {
    if (price == null || price <= 0) {
      throw new PriceLessThanZeroException(price);
    }
  }

  @Invariant("name_length_limit")
  private void validateNameLength(String name) {
    if (name.length() > MAX_GOODS_NAME) {
      throw new GoodsNameTooLongException(name);
    }
  }

  public Goods load(String aggregateId, String code, String name, Double price) {
    Goods goods = new Goods(aggregateId, code, name, price);
    autowireCapableBeanFactory.autowireBean(goods);
    return goods;
  }
}
