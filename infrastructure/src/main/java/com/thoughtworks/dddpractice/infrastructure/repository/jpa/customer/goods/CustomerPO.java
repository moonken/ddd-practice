package com.thoughtworks.dddpractice.infrastructure.repository.jpa.customer.goods;

import com.thoughtworks.dddpractice.domain.customer.Customer;
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
@Table(name = "customers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "aggregate_status='ACTIVE'")
public class CustomerPO extends BaseAggregateRootPO<Customer> {

  @Id
  @Column(name = "id")
  @Setter
  protected String aggregateId;

  private String name;

  private boolean vip;

  @Override
  protected void update(Customer aggregate) {
    this.name = aggregate.getName();
    this.vip = aggregate.isVip();
  }
}
