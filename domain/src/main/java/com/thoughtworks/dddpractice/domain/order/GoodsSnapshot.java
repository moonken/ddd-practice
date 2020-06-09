package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.framework.annotations.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ValueObject
public class GoodsSnapshot {
  private String code;
  private String name;
  private BigDecimal price;

  public GoodsSnapshot(Goods goods) {
    this.code = goods.getCode();
    this.name = goods.getName();
    this.price = goods.getPrice();
  }
}
