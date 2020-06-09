package com.thoughtworks.dddpractice.application.service;

import com.thoughtworks.dddpractice.infrastructure.repository.jpa.customer.goods.CustomerPO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.customer.goods.JpaCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerReadService {
  private final JpaCustomerRepository jpaCustomerRepository;

  public List<CustomerPO> getAllPO() {
    return jpaCustomerRepository.findAll();
  }
}
