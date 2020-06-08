package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.domain.order.dto.OrderItemDTO;
import com.thoughtworks.dddpractice.domain.order.dto.OrderDTO;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderFactoryTest {

  @Mock
  DomainEventPublisher domainEventPublisher;

  @Mock
  OrderRepository orderRepository;

  OrderFactory orderFactory;

  String customerId = UUID.randomUUID().toString();

  @BeforeEach
  void setUp() {
    orderFactory = new OrderFactory(domainEventPublisher);
  }

  @Test
  void should_create_order_successful() {
    String appleId = UUID.randomUUID().toString();
    String orangeId = UUID.randomUUID().toString();
    Order order = orderFactory.create(OrderDTO.builder()
      .customerId(customerId)
      .items(asList(
        new OrderItemDTO(null, appleId, 3.5),
        new OrderItemDTO(null, orangeId, 2d)))
      .build());


    assertThat(order.getCustomerId(), is(customerId));
    assertThat(order.getItems().size(), is(2));
    assertThat(order.getItems().stream().map(OrderItem::getGoodsId).collect(Collectors.toList()),
      containsInAnyOrder(appleId, orangeId));
    assertThat(order.getItems().get(0).getEntityId(), is(notNullValue()));
    assertThat(order.getItems().get(1).getEntityId(), is(notNullValue()));
  }
}
