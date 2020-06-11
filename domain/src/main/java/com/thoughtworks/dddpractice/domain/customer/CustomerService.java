package com.thoughtworks.dddpractice.domain.customer;

import com.thoughtworks.dddpractice.domain.order.OrderCreatedEvent;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainService;
import com.thoughtworks.dddpractice.framework.annotations.event.EventListener;
import com.thoughtworks.dddpractice.framework.annotations.event.EventListeners;
import lombok.AllArgsConstructor;

@DomainService
@AllArgsConstructor
@EventListeners
public class CustomerService {

  private final CustomerFactory customerFactory;
  private final CustomerRepository customerRepository;

  public Customer create(String name) {
    Customer goods = customerFactory.create(name);
    customerRepository.save(goods);
    return goods;
  }

  @EventListener
  public void onOrderCreated(OrderCreatedEvent event) {
  }
}
