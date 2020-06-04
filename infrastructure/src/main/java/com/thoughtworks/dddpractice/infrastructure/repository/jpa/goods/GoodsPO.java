package com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods;

import com.thoughtworks.dddpractice.framework.support.infrastructure.repository.jpa.BaseAggregateRootPO;
import com.thoughtworks.dddpractice.domain.goods.Goods;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "goods")
@Getter
@NoArgsConstructor
public class GoodsPO extends BaseAggregateRootPO<Goods> {
  private String code;
  private String name;
  private Double price;

  public GoodsPO(String aggregateId, String code, String name, Double price) {
    this.aggregateId = aggregateId;
    this.code = code;
    this.name = name;
    this.price = price;
  }

  @Override
  protected void update(Goods aggregate) {
    this.code = aggregate.getCode();
    this.name = aggregate.getName();
    this.price = aggregate.getPrice();
  }
}
