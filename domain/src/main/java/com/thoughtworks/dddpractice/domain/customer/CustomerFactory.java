package com.thoughtworks.dddpractice.domain.customer;

import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import lombok.AllArgsConstructor;

@DomainFactory
@AllArgsConstructor
public class CustomerFactory {

  public Customer create(String name) {
    return new Customer(name);
  }

  public Customer load(String aggregateId, String name, boolean isVip) {
    return new Customer(aggregateId, name, isVip);
  }

}
