package com.thoughtworks.dddpractice.domain.customer;

import com.thoughtworks.dddpractice.framework.annotations.domain.AggregateRoot;
import com.thoughtworks.dddpractice.framework.support.IdGenerator;
import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRoot;
import lombok.Getter;

@AggregateRoot
@Getter
public class Customer extends BaseAggregateRoot {
  private String aggregateId;
  private String name;
  private boolean vip;

  Customer(String name) {
    this.aggregateId = IdGenerator.nextId();
    this.name = name;
    this.vip = false;
  }

  Customer(String aggregateId, String name, Boolean isVip) {
    this.aggregateId = aggregateId;
    this.name = name;
    this.vip = isVip;
  }

  public void markAsVip() {
    this.vip = true;
  }
}
