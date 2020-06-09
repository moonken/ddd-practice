package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.domain.goods.GoodsFactory;
import com.thoughtworks.dddpractice.domain.goods.GoodsRepository;
import com.thoughtworks.dddpractice.domain.goods.dto.GoodsDTO;
import com.thoughtworks.dddpractice.domain.order.dto.OrderItemDTO;
import com.thoughtworks.dddpractice.domain.order.dto.OrderDTO;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderFactoryTest {

  @Mock
  DomainEventPublisher domainEventPublisher;

  @Mock
  GoodsRepository goodsRepository;

  OrderFactory orderFactory;

  GoodsFactory goodsFactory;

  String customerId = UUID.randomUUID().toString();

  @BeforeEach
  void setUp() {
    goodsFactory = new GoodsFactory(domainEventPublisher, goodsRepository);
    orderFactory = new OrderFactory(domainEventPublisher, goodsRepository);
  }

  @Test
  void should_create_order_successful() {
    Goods apple =
      goodsFactory.create(GoodsDTO.builder().code("01").name("apple").price(BigDecimal.valueOf(3.5)).build());
    Goods orange =
      goodsFactory.create(GoodsDTO.builder().code("02").name("orange").price(BigDecimal.valueOf(2)).build());
    when(goodsRepository.load(apple.getAggregateId())).thenReturn(apple);
    when(goodsRepository.load(orange.getAggregateId())).thenReturn(orange);

    Order order = orderFactory.create(OrderDTO.builder()
      .customerId(customerId)
      .items(asList(
        new OrderItemDTO(apple.getAggregateId(), 3.5d),
        new OrderItemDTO(orange.getAggregateId(), 2d)))
      .build());


    assertThat(order.getCustomerId(), is(customerId));
    assertThat(order.getItems().size(), is(2));
    assertThat(order.getItems().stream().map(OrderItem::getGoodsId).collect(Collectors.toList()),
      containsInAnyOrder(apple.getAggregateId(), orange.getAggregateId()));
    assertThat(order.getItems().get(0).getEntityId(), is(notNullValue()));
    assertThat(order.getItems().get(1).getEntityId(), is(notNullValue()));
  }
}
