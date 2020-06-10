package com.thoughtworks.dddpractice.domain.customer;

import com.thoughtworks.dddpractice.domain.customer.dto.CustomerDTO;
import com.thoughtworks.dddpractice.domain.goods.dto.GoodsDTO;
import com.thoughtworks.dddpractice.domain.goods.exception.GoodsNameTooLongException;
import com.thoughtworks.dddpractice.domain.goods.exception.PriceLessThanZeroException;
import com.thoughtworks.dddpractice.framework.annotations.domain.AggregateRoot;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariant;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariants;
import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRoot;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.Getter;

@AggregateRoot
@Getter
public class Customer extends BaseAggregateRoot {
  private String name;
  private boolean vip;

  Customer(String generatedId, CustomerDTO customerDTO) {
    this.aggregateId = generatedId;
    this.name = customerDTO.getName();
    this.vip = customerDTO.isVip();
  }

  Customer(CustomerDTO customerDTO) {
    this.aggregateId = customerDTO.getAggregateId();
    this.name = customerDTO.getName();
    this.vip = customerDTO.isVip();
  }

  public void markAsVip() {
    this.vip = true;
  }
}
