package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.domain.goods.dto.GoodsDTO;
import com.thoughtworks.dddpractice.domain.goods.exception.GoodsCodeDuplicatedException;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
class GoodsFactoryTest {

  @Mock
  GoodsRepository goodsRepository;

  GoodsFactory goodsFactory;

  @BeforeEach
  void setUp() {
    goodsFactory = new GoodsFactory(goodsRepository);
  }

  @Test
  void should_create_goods_successful() {
    Mockito.when(goodsRepository.findByCode("001")).thenReturn(Optional.empty());
    Goods goods = goodsFactory.create(GoodsDTO.builder()
      .code("001")
      .name("name")
      .price(BigDecimal.valueOf(3.5))
      .build());

    assertThat(goods.getCode(), is("001"));
    assertThat(goods.getName(), is("name"));
    assertThat(goods.getPrice(), is(BigDecimal.valueOf(3.5)));
  }

  @Test
  void should_failed_create_goods_when_code_exists() {
    Mockito.when(goodsRepository.findByCode("001")).thenReturn(Optional.of(mock(Goods.class)));
    assertThrows(GoodsCodeDuplicatedException.class, () -> goodsFactory.create(GoodsDTO.builder()
      .code("001")
      .name("name")
      .price(BigDecimal.valueOf(3.5))
      .build()));
  }
}
