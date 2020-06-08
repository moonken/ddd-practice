package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.domain.goods.exception.GoodsNameTooLongException;
import com.thoughtworks.dddpractice.domain.goods.exception.PriceLessThanZeroException;
import com.thoughtworks.dddpractice.domain.goods.vo.GoodsVO;
import com.thoughtworks.dddpractice.framework.annotations.domain.AggregateRoot;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariant;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariants;
import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRoot;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.Getter;

@Invariants({
  "name_length_limit: name can not too long",
  "price_must_great_than_0: price must great than 0"
})
@AggregateRoot
@Getter
public class Goods extends BaseAggregateRoot {

  public static final int MAX_GOODS_NAME = 20;

  private String code;
  private String name;
  private Double price;

  Goods(GoodsVO goodsVO, DomainEventPublisher domainEventPublisher) {
    super(domainEventPublisher);
    this.aggregateId = goodsVO.getAggregateId();
    this.code = goodsVO.getCode();
    this.name = goodsVO.getName();
    this.price = goodsVO.getPrice();
  }

  Goods(String id, GoodsVO goodsVO, DomainEventPublisher domainEventPublisher) {
    super(domainEventPublisher);
    validateNameLength(goodsVO.getName());
    validatePrice(goodsVO.getPrice());

    this.aggregateId = id;
    this.code = goodsVO.getCode();
    this.name = goodsVO.getName();
    this.price = goodsVO.getPrice();
  }

  public void rename(String name) {
    validateNameLength(name);
    this.name = name;
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
}
