package com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods;

import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRootPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goods")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "aggregate_status='ACTIVE'")
public class GoodsPO extends BaseAggregateRootPO<Goods> {

  @Id
  @Column(name = "id")
  @Setter
  protected String aggregateId;

  private String code;
  private String name;
  private Double price;

  @Override
  protected void update(Goods aggregate) {
    this.code = aggregate.getCode();
    this.name = aggregate.getName();
    this.price = aggregate.getPrice();
  }
}
