package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.framework.annotations.domain.AggregateRoot;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainEntity;
import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRoot;
import lombok.Getter;

import java.util.UUID;

@DomainEntity
@AggregateRoot
@Getter
public class Goods extends BaseAggregateRoot {
  private String code;
  private String name;
  private Double price;

  Goods(String aggregateId, String code, String name, Double price) {
    this.aggregateId = aggregateId;
    this.code = code;
    this.name = name;
    this.price = price;
  }

  Goods(String code, String name, Double price) {
    this.aggregateId = UUID.randomUUID().toString();
    this.code = code;
    this.name = name;
    this.price = price;
  }

  public void rename(String name) {
    this.name = name;
  }
}
