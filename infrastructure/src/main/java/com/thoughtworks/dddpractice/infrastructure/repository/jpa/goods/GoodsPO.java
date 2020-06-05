package com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods;

import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRootPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "goods")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "aggregate_status='ACTIVE'")
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
