package com.thoughtworks.dddpractice.domain.customer;

import com.thoughtworks.dddpractice.domain.customer.dto.CustomerDTO;
import com.thoughtworks.dddpractice.domain.goods.dto.GoodsDTO;
import com.thoughtworks.dddpractice.domain.goods.exception.GoodsCodeDuplicatedException;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariant;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariants;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.AllArgsConstructor;

import java.util.UUID;

@DomainFactory
@AllArgsConstructor
public class CustomerFactory {

  private final DomainEventPublisher domainEventPublisher;

  public Customer create(CustomerDTO customerDTO) {
    return new Customer(UUID.randomUUID().toString(), customerDTO, domainEventPublisher);
  }

  public Customer load(CustomerDTO customerDTO) {
    return new Customer(customerDTO, domainEventPublisher);
  }

}
