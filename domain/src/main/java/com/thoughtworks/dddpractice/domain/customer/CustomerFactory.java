package com.thoughtworks.dddpractice.domain.customer;

import com.thoughtworks.dddpractice.domain.customer.dto.CustomerDTO;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import lombok.AllArgsConstructor;

import java.util.UUID;

@DomainFactory
@AllArgsConstructor
public class CustomerFactory {

  public Customer create(CustomerDTO customerDTO) {
    return new Customer(UUID.randomUUID().toString(), customerDTO);
  }

  public Customer load(CustomerDTO customerDTO) {
    return new Customer(customerDTO);
  }

}
