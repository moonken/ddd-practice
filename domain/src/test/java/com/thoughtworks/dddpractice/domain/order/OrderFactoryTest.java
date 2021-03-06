package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.domain.goods.GoodsFactory;
import com.thoughtworks.dddpractice.domain.goods.GoodsRepository;
import com.thoughtworks.dddpractice.framework.support.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderFactoryTest {

  @Mock
  GoodsRepository goodsRepository;

  OrderFactory orderFactory;

  GoodsFactory goodsFactory;

  String customerId = IdGenerator.nextId();

  @BeforeEach
  void setUp() {
    goodsFactory = new GoodsFactory(goodsRepository);
    orderFactory = new OrderFactory(goodsRepository);
  }

  @Test
  void should_create_order_successful() {
    Goods apple =
      goodsFactory.create("01", "apple", BigDecimal.valueOf(3.5));
    Goods orange =
      goodsFactory.create("02", "orange", BigDecimal.valueOf(3.5));
    when(goodsRepository.load(apple.getAggregateId())).thenReturn(apple);
    when(goodsRepository.load(orange.getAggregateId())).thenReturn(orange);

    Order order = orderFactory.create(customerId,
      asList(
        OrderItem.builder().goodsId(apple.getAggregateId()).quantity(3.5d).build(),
        OrderItem.builder().goodsId(orange.getAggregateId()).quantity(2d).build())
    );

    assertThat(order.getCustomerId(), is(customerId));
    assertThat(order.getItems().size(), is(2));
    assertThat(order.getItems().stream().map(OrderItem::getGoodsId).collect(Collectors.toList()),
      containsInAnyOrder(apple.getAggregateId(), orange.getAggregateId()));
    assertThat(order.getItems().get(0).getEntityId(), is(notNullValue()));
    assertThat(order.getItems().get(1).getEntityId(), is(notNullValue()));
  }
}
