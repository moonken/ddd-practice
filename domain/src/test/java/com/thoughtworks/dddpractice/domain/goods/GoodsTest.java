package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.domain.goods.dto.GoodsDTO;
import com.thoughtworks.dddpractice.domain.goods.exception.GoodsNameTooLongException;
import com.thoughtworks.dddpractice.domain.goods.exception.PriceLessThanZeroException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GoodsTest {
  @Test
  void should_failed_create_goods_if_name_too_long() {
    assertThrows(GoodsNameTooLongException.class, () -> {
      new Goods(UUID.randomUUID().toString(),
        GoodsDTO.builder()
          .code("001")
          .name("looooooooooooooooooooooooooooooong")
          .price(BigDecimal.valueOf(3.5))
          .build());
    });
  }

  @Test
  void should_failed_create_goods_if_price_less_than_zero() {
    assertThrows(PriceLessThanZeroException.class, () -> {
      new Goods(UUID.randomUUID().toString(),
        GoodsDTO.builder()
          .code("001")
          .name("name")
          .price(BigDecimal.valueOf(-3.5))
          .build());
    });
  }
}
