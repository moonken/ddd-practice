package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.domain.goods.dto.GoodsDTO;
import com.thoughtworks.dddpractice.domain.goods.exception.GoodsNameTooLongException;
import com.thoughtworks.dddpractice.domain.goods.exception.PriceLessThanZeroException;
import com.thoughtworks.dddpractice.framework.annotations.domain.AggregateRoot;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariant;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariants;
import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRoot;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.Getter;

import java.math.BigDecimal;

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
  private BigDecimal price;

  Goods(GoodsDTO goodsDTO, DomainEventPublisher domainEventPublisher) {
    super(domainEventPublisher);
    this.aggregateId = goodsDTO.getAggregateId();
    this.code = goodsDTO.getCode();
    this.name = goodsDTO.getName();
    this.price = goodsDTO.getPrice();
  }

  Goods(String generatedId, GoodsDTO goodsDTO, DomainEventPublisher domainEventPublisher) {
    this(goodsDTO, domainEventPublisher);
    validateNameLength(goodsDTO.getName());
    validatePrice(goodsDTO.getPrice());
    this.aggregateId = generatedId;
  }

  public void rename(String name) {
    validateNameLength(name);
    this.name = name;
  }

  @Invariant("price_must_great_than_0")
  private void validatePrice(BigDecimal price) {
    if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
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
