package com.thoughtworks.dddpractice.domain.customer;

import com.thoughtworks.dddpractice.domain.customer.dto.CustomerDTO;
import com.thoughtworks.dddpractice.domain.order.OrderCreatedEvent;
import com.thoughtworks.dddpractice.domain.order.OrderRepository;
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
  private final OrderRepository orderRepository;

  public Customer create(CustomerDTO customerDTO) {
    Customer goods = customerFactory.create(customerDTO);
    customerRepository.save(goods);
    return goods;
  }

  @EventListener
  public void onOrderCreated(OrderCreatedEvent event) {
  }
}
